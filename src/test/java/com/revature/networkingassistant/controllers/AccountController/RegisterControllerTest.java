package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
class RegisterControllerTest {

    private JsonRequestBody<Account> requestBody;
    private Account testAccount;

    @Autowired
    private AccountRepo accountRepo;

    @After
    public void rollback() {
        accountRepo.delete(testAccount);
    }

    @Test
    void registerAccountTest() throws JsonProcessingException {
        requestBody = new JsonRequestBody<>();
        testAccount = new Account();
        byte[] test = "test".getBytes();
        SessionToken token = new SessionToken();
        testAccount.setEmail("test@test.test");
        testAccount.setPasswordHash("test");
        testAccount.setFirstName("test");
        testAccount.setLastName("test");
        testAccount.setBackground("test");
        testAccount.setPhone("123-456-7890");
        testAccount.setZipCode("12345");
        testAccount.setAttachment(test);
        requestBody.setObject(testAccount);
        requestBody = new JsonRequestBody<>();
        requestBody.setObject(testAccount);
        System.out.println(requestBody);
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .put("/api/register")
                .then()
                .statusCode(200)
                .assertThat().body("email", equalTo(testAccount.getEmail()));
    }
}