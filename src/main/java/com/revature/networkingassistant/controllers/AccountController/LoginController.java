package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.RequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public abstract class LoginController {

    private AccountRepo accountRepo;
    private SessionTokenRepo sessionTokenRepo;

    public LoginController() { }

    @Autowired
    public LoginController(AccountRepo accountRepo, SessionTokenRepo sessionTokenRepo) {
        this.accountRepo = accountRepo;
        this.sessionTokenRepo = sessionTokenRepo;
    }

    @Transactional
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public SessionToken verifyCredentials(@org.springframework.web.bind.annotation.RequestBody RequestBody<Account> requestBody) {
        //account exists
        if (accountRepo.existsByEmail((requestBody.getObject().getEmail()))) {
            Account account = accountRepo.findByEmail(requestBody.getObject().getEmail());
            //passwords match
            if (checkPassword(requestBody.getObject().getPasswordHash(), account.getPasswordHash())) {

                //if token does not exist, create new one
                if (!sessionTokenRepo.existsByAccountId(account.getId())) {
                    SessionToken token = new SessionToken();
                    token.setAccountId(account.getId());
                    return sessionTokenRepo.save(token);
                }
                //return same token if already exists
                else return sessionTokenRepo.findByAccountId(account.getId());
            }
        }
        return null;
    }

    private boolean checkPassword(String password_plaintext, String stored_hash) {
        if(stored_hash == null || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        return BCrypt.checkpw(password_plaintext, stored_hash);
    }
}
