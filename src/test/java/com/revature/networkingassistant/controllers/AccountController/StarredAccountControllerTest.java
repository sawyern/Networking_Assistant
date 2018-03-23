package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.controllers.TestUtil;
import com.revature.networkingassistant.repositories.AccountRepo;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class StarredAccountControllerTest {

    private JsonRequestBody<Account> requestBody;
    private Account testAccount;
    private Account testAccount2;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private AccountRepo accountRepo;

    @Before
    public void setup() {
        testAccount = testUtil.createTestAccount();
        testAccount2 = testUtil.createTestAccount("other@gmail.com");

        //create test token
        SessionToken token = testUtil.loginTestUser(testAccount);
        testAccount.setEmail("updated@test.com");
        requestBody = new JsonRequestBody<>(token, testAccount2);
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(testAccount);
        testUtil.removeSessionToken(requestBody.getToken());
        testUtil.removeTestAccount(testAccount2);
    }

    @Test
    public void testStar() throws Exception {
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .put("/api/account/addStar")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat().body("id", equalTo(testAccount2.getId()));
//        Assert.assertTrue(accountRepo.findById(testAccount.getId()).get().getMyStarredList().contains(testAccount2));
    }

    @Test
    public void isStarredByIdTest() throws IOException {
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
        .when()
                .get("/api/account/isStarredById/" + testAccount2.getId())
        .then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("id", equalTo(testAccount2.getId()));
    }
}
