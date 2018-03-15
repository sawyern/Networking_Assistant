package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    private SessionTokenRepo sessionTokenRepo;

    public LogoutController() { }

    @Autowired
    public LogoutController(SessionTokenRepo sessionTokenRepo) {
        this.sessionTokenRepo = sessionTokenRepo;
    }

    @Transactional
    @RequestMapping(value = "/api/logout", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void logout(@RequestBody JsonRequestBody<Account> jsonRequestBody) {
        //invalidate token
        sessionTokenRepo.delete(jsonRequestBody.getToken());
    }
}
