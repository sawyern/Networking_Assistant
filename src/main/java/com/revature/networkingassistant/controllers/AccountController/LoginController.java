package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import com.revature.networkingassistant.services.AccountServices.LoginService;
import com.revature.networkingassistant.services.AccountServices.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LoginService loginService;

    @Transactional
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SessionToken> verifyCredentials(@RequestBody JsonRequestBody<Account> jsonRequestBody) {
        return loginService.verifyCredentials(jsonRequestBody);
    }
}
