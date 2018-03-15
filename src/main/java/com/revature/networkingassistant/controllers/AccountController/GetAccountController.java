package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class GetAccountController {
    private AccountRepo accountRepo;
    private SessionTokenRepo sessionTokenRepo;

    public GetAccountController() { }

    @Autowired
    public GetAccountController(AccountRepo accountRepo, SessionTokenRepo sessionTokenRepo) {
        this.accountRepo = accountRepo;
        this.sessionTokenRepo = sessionTokenRepo;
    }

    @Transactional
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public SessionToken verifyCredentials(@RequestBody JsonRequestBody<Account> jsonRequestBody) {
        return null;
    }

}
