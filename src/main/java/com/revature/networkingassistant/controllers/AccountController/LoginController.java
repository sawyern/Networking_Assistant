package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private SessionTokenRepo sessionTokenRepo;

    @Transactional
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SessionToken> verifyCredentials(@RequestBody JsonRequestBody<Account> jsonRequestBody) {
        //account exists
        if (accountRepo.existsByEmail((jsonRequestBody.getObject().getEmail()))) {
            Account account = accountRepo.findByEmail(jsonRequestBody.getObject().getEmail());
            //passwords match
            if (checkPassword(jsonRequestBody.getObject().getPasswordHash(), account.getPasswordHash())) {
                //if token does not exist, create new one
                if (!sessionTokenRepo.existsByAccountId(account.getId())) {
                    SessionToken token = new SessionToken();
                    token.setAccountId(account.getId());
                    return new ResponseEntity<>(sessionTokenRepo.save(token), HttpStatus.OK);
                }
                //return same token if already exists
                else return new ResponseEntity<>(sessionTokenRepo.findByAccountId(account.getId()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new SessionToken(), HttpStatus.BAD_REQUEST);
    }

    private boolean checkPassword(String password_plaintext, String stored_hash) {
        if(stored_hash == null || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        return BCrypt.checkpw(password_plaintext, stored_hash);
    }
}
