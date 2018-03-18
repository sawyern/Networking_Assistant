package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.services.AccountServices.GetAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class GetAccountController {
    private GetAccountService getAccountService;

    public GetAccountController() { }

    @Autowired
    public GetAccountController(GetAccountService getAccountService) {
        this.getAccountService = getAccountService;
    }

    @RequestMapping(value = "/api/account/getByEmail/{email}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> getByEmail(@PathVariable String email) {
        return getAccountService.getByEmail(email);
    }

    @RequestMapping(value = "/api/account/getByFirstName/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> getByFirstName(@PathVariable String name) {
        return getAccountService.getByFirstName(name);
    }

    @RequestMapping(value = "/api/account/getByLastName/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> getByLastName(@PathVariable String name) {
        return getAccountService.getByLastName(name);
    }

    @RequestMapping(value = "/api/accounts/getByPartialEmail/{partialEmail}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Account>> getByPartialEmail(@PathVariable String partialEmail) {
        return getAccountService.getByPartialEmail(partialEmail);
    }

    @RequestMapping(value = "/api/accounts/getByPartialFirstName/{partialName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Account>> getByPartialFirstName(@PathVariable String partialName) {
        return getAccountService.getByPartialFirstName(partialName);
    }

    @RequestMapping(value = "/api/accounts/getByPartialLastName/{partialName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Account>> getByPartialLastName(@PathVariable String partialName) {
        return getAccountService.getByPartialLastName(partialName);
    }
}
