package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import com.revature.networkingassistant.services.AccountServices.RegisterService;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jayway.restassured.RestAssured.given;

class LogoutControllerTest {

    private JsonRequestBody<Account> requestBody;
    private Account testAccount;

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private SessionTokenRepo sessionTokenRepo;

    @Before
    public void setup() {
        SessionToken sessionToken = new SessionToken();

        testAccount = new Account();
        testAccount.setEmail("snovak@test.com");
        testAccount.setPasswordHash(RegisterService.hashPassword("password"));

        testAccount = accountRepo.save(testAccount);

        sessionToken.setAccountId(testAccount.getId());
        sessionToken = sessionTokenRepo.save(sessionToken);

        requestBody = new JsonRequestBody<>();
        requestBody.setObject(testAccount);
        requestBody.setToken(sessionToken);

        testAccount.setPasswordHash("password");
    }

    @Test
    void logout() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/logout")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}