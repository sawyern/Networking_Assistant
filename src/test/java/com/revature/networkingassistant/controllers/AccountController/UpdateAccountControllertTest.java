package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.controllers.TestUtil;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class UpdateAccountControllertTest {

    private JsonRequestBody<Account> requestBody;
    private Account testAccount;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TestUtil testUtil;

    @Before
    public void setup() {
        testAccount = testUtil.createTestAccount();
        //create test token
        SessionToken token = testUtil.loginTestUser(testAccount);
        testAccount.setEmail("updated@test.com");
        requestBody = new JsonRequestBody<>(token, testAccount);
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(testAccount);
        testUtil.removeSessionToken(requestBody.getToken());
    }

    @Test
    public void testUpdate() throws Exception {
        given()
            .body(mapper.writeValueAsString(requestBody))
            .contentType(ContentType.JSON)
        .when()
            .post("/api/account/update")
        .then()
            .statusCode(HttpStatus.SC_ACCEPTED);
    }
}
