package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
class GetAccountControllerTest {

    @Autowired
    private static JsonRequestBody requestBody;

    @Autowired
    private static Account testAccount;

    @Before
    public static void setup() {
        requestBody = new JsonRequestBody<>();
        testAccount = new Account();
        byte[] test = "test".getBytes();
        testAccount.setEmail("test@test.test");
        testAccount.setPasswordHash("Pieis3141");
        testAccount.setFirstName("test");
        testAccount.setLastName("test");
        testAccount.setBackground("test");
        testAccount.setPhone("123-456-7890");
        testAccount.setZipCode("12345");
        testAccount.setAttachment(test);
        requestBody = new JsonRequestBody<>();
        requestBody.setObject(testAccount);
    }
    @Test
    void getByEmail() throws JsonProcessingException {
        setup();
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
    void getByFirstName() throws JsonProcessingException {
        setup();
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
    void getByLastName() throws JsonProcessingException {
        setup();
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
    void getByPartialEmail() throws JsonProcessingException {
        setup();
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
    void getByPartialFirstName() throws JsonProcessingException {
        setup();
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
    void getByPartialLastName() throws JsonProcessingException {
        setup();
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