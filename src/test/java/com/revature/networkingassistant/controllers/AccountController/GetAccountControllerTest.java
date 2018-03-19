package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.AccountServices.RegisterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class GetAccountControllerTest {

    private static JsonRequestBody requestBody;
    private static Account testAccount;

    @Autowired
    RegisterService registerService;

    @Before
    public void setup() {
        requestBody = new JsonRequestBody<>();
        testAccount = new Account();
        byte[] test = "test".getBytes();
        testAccount.setEmail("test@test.test");
        testAccount.setPasswordHash("password1");
        testAccount.setFirstName("test");
        testAccount.setLastName("test");
        testAccount.setBackground("test");
        testAccount.setPhone("223-456-7890");
        testAccount.setZipCode("12345");
        testAccount.setAttachment(test);
        requestBody = new JsonRequestBody<>();
        requestBody.setObject(testAccount);
        registerService.registerAccount(requestBody);
    }

    @Test
    public void getByEmail() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .get("/api/account/getByEmail/"+ testAccount.getEmail())
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("email", equalTo(testAccount.getEmail()));
    }

    @Test
    public void getByFirstName() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .get("/api/account/getByFirstName/" + testAccount.getFirstName())
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("firstName", equalTo(testAccount.getFirstName()));
    }

    @Test
    public void getByLastName() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .get("/api/account/getByLastName/"+ testAccount.getLastName())
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("lastName", equalTo(testAccount.getLastName()));
    }

    @Test
    public void getByPartialEmail() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .get("/api/accounts/getByPartialEmail/"+ testAccount.getEmail().substring(0, testAccount.getEmail().length()/2))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void getByPartialFirstName() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .get("/api/accounts/getByPartialFirstName/"+ testAccount.getFirstName().substring(0, testAccount.getFirstName().length()/2))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void getByPartialLastName() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .get("/api/accounts/getByPartialLastName/"+ testAccount.getLastName().substring(0, testAccount.getLastName().length()/2))
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}