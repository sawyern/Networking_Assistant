package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.RequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    SessionTokenRepo sessionTokenRepo;

    @Transactional
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public SessionToken verifyCredentials(@org.springframework.web.bind.annotation.RequestBody RequestBody<Account> requestBody) {
        //account exists
        if (accountRepo.existsByEmail((requestBody.getObject().getEmail()))) {
            Account account = accountRepo.findByEmail(requestBody.getObject().getEmail());
            //passwords match
            if (account.getPasswordHash().equals(requestBody.getObject().getPasswordHash())) {

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
}
