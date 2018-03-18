package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import com.revature.networkingassistant.services.AccountServices.RegisterService;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class LoginControllerTest {

    private Account testAccount;

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private SessionTokenRepo sessionTokenRepo;

    @After
    public void rollback() {
        accountRepo.delete(testAccount);
    }

    @Test
    public void loginTest() throws IOException {
        SessionToken sessionToken = new SessionToken();

        testAccount = new Account();
        testAccount.setEmail("snovak@test.com");
        testAccount.setPasswordHash(RegisterService.hashPassword("password"));

        testAccount = accountRepo.save(testAccount);

        sessionToken.setAccountId(testAccount.getId());
        sessionToken = sessionTokenRepo.save(sessionToken);

        JsonRequestBody<Account> requestBody = new JsonRequestBody<>();
        requestBody.setObject(testAccount);
        requestBody.setToken(sessionToken);

        testAccount.setPasswordHash("password");

        ObjectMapper mapper = new ObjectMapper();

        given()
            .body(mapper.writeValueAsString(requestBody))
            .contentType(ContentType.JSON)
        .when()
            .post("/api/login")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .assertThat().body("id", is(requestBody.getToken().getId()))
            .assertThat().body("accountId", equalTo(requestBody.getToken().getAccountId()));
    }
}
