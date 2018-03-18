package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.AccountServices.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Transactional
    @RequestMapping(value = "/api/register", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> registerAccount(@RequestBody JsonRequestBody<Account> jsonRequestBody) {
        return registerService.registerAccount(jsonRequestBody);
    }
}
