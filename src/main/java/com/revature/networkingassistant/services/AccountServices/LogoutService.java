package com.revature.networkingassistant.services.AccountServices;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LogoutService {
    private SessionTokenRepo sessionTokenRepo;

    public LogoutService() { }

    @Autowired
    public LogoutService(SessionTokenRepo sessionTokenRepo) {
        this.sessionTokenRepo = sessionTokenRepo;
    }

    @Transactional
    public HttpStatus logout(@RequestBody JsonRequestBody<Account> jsonRequestBody) {
        try {
            //invalidate token
            sessionTokenRepo.delete(jsonRequestBody.getToken());
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
