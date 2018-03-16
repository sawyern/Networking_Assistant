package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @org.junit.jupiter.api.Test
    void verifyCredentials() {
        byte[] test = "test".getBytes();
        Account account = new Account("test@test.test", "test", "test", "test", "test", test);
        SessionToken token = new SessionToken();
        JsonRequestBody<Account> requestBody = new JsonRequestBody<>();
        requestBody.setObject(account);
    }
}