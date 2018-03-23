package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.controllers.TestUtil;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class LoginControllerTest {
    private JsonRequestBody<Account> requestBody;
    private Account testAccount;

    @Autowired
    private TestUtil testUtil;
    @Autowired
    private SessionTokenRepo sessionTokenRepo;

    @Before
    public void setup() {
        testAccount = testUtil.createTestAccount();
        testAccount.setPasswordHash("password");
        requestBody = new JsonRequestBody<>();
        requestBody.setObject(testAccount);
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(testAccount);
        testUtil.removeSessionToken(sessionTokenRepo.findByAccountId(testAccount.getId()));
    }

    @Test
    @Transactional
    public void loginTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        given()
            .body(mapper.writeValueAsString(requestBody))
            .contentType(ContentType.JSON)
        .when()
            .post("/api/login")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .assertThat().body("id", equalTo(sessionTokenRepo.findByAccountId(testAccount.getId()).getId()))
            .assertThat().body("accountId", equalTo(sessionTokenRepo.findByAccountId(testAccount.getId()).getAccountId()));
    }
}
