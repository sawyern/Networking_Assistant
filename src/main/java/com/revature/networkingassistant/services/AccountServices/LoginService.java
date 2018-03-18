package com.revature.networkingassistant.services.AccountServices;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private AccountRepo accountRepo;
    private SessionTokenRepo sessionTokenRepo;

    public LoginService() { }

    @Autowired
    public LoginService(AccountRepo accountRepo, SessionTokenRepo sessionTokenRepo) {
        this.accountRepo = accountRepo;
        this.sessionTokenRepo = sessionTokenRepo;
    }

    @Transactional
    public ResponseEntity<SessionToken> verifyCredentials(JsonRequestBody<Account> jsonRequestBody) {
        try {
            //account exists
            if (accountRepo.existsByEmail((jsonRequestBody.getObject().getEmail()))) {
                Account account = accountRepo.findByEmail(jsonRequestBody.getObject().getEmail());
                //passwords match
                if (checkPassword(jsonRequestBody.getObject().getPasswordHash(), account.getPasswordHash())) {
                    //if token does not exist, create new one
                    if (!sessionTokenRepo.existsByAccountId(account.getId())) {
                        SessionToken token = new SessionToken();
                        token.setAccountId(account.getId());
                        token = sessionTokenRepo.save(token);
                        return new ResponseEntity<>(token, HttpStatus.OK);
                    }
                    //return same token if already exists
                    else return new ResponseEntity<>(sessionTokenRepo.findByAccountId(account.getId()), HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionToken(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new SessionToken(), HttpStatus.UNAUTHORIZED);
    }

    private boolean checkPassword(String password_plaintext, String stored_hash) {
        if(stored_hash == null || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        return BCrypt.checkpw(password_plaintext, stored_hash);
    }
}
