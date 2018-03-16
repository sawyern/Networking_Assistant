package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

public class GetAccountController {
    private AccountRepo accountRepo;
    private SessionTokenRepo sessionTokenRepo;

    public GetAccountController() { }

    @Autowired
    public GetAccountController(AccountRepo accountRepo, SessionTokenRepo sessionTokenRepo) {
        this.accountRepo = accountRepo;
        this.sessionTokenRepo = sessionTokenRepo;
    }

    @RequestMapping(value = "/api/account/getByEmail/{email}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Account getByEmail(@RequestBody JsonRequestBody<Account> jsonRequestBody, @PathVariable String email) {
        return accountRepo.findByEmail(email);
    }

    @RequestMapping(value = "/api/account/getByFirstName/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Account getByFirstName(@RequestBody JsonRequestBody<Account> jsonRequestBody, @PathVariable String name) {
        return accountRepo.findByFirstName(name);
    }

    @RequestMapping(value = "/api/account/getByLastName/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Account getByLastName(@RequestBody JsonRequestBody<Account> jsonRequestBody, @PathVariable String name) {
        return accountRepo.findByLastName(name);
    }

    @RequestMapping(value = "/api/accounts/getByPartialEmail/{partialEmail}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<Account> getByPartialEmail(@RequestBody JsonRequestBody<Account> jsonRequestBody, @PathVariable String partialEmail) {
        return accountRepo.findAccountsByEmailContaining(partialEmail);
    }

    @RequestMapping(value = "/api/accounts/getByPartialFirstName/{partialEmail}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<Account> getByPartialFirstName(@RequestBody JsonRequestBody<Account> jsonRequestBody, @PathVariable String partialEmail) {
        return accountRepo.findAccountsByFirstNameContaining(partialEmail);
    }

    @RequestMapping(value = "/api/accounts/getByPartialLastName/{partialEmail}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<Account> getByPartialLastName(@RequestBody JsonRequestBody<Account> jsonRequestBody, @PathVariable String partialEmail) {
        return accountRepo.findAccountsByLastNameContaining(partialEmail);
    }
}
