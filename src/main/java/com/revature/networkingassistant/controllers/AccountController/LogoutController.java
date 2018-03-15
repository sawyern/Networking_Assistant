package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.RequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    SessionTokenRepo sessionTokenRepo;

    @Transactional
    @RequestMapping(value = "/api/logout", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void logout(@org.springframework.web.bind.annotation.RequestBody RequestBody<Account> requestBody) {
        //invalidate token
        sessionTokenRepo.delete(requestBody.getToken());
    }
}
