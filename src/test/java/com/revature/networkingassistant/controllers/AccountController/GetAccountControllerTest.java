package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.controllers.TestUtil;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.junit.After;
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

    @Autowired
    private TestUtil testUtil;

    private JsonRequestBody<Account> requestBody = new JsonRequestBody<>();

    @Before
    public void setup() {
        requestBody.setObject(testUtil.createTestAccount());
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(requestBody.getObject());
    }

    @Test
    public void getByEmail()  {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/account/getByEmail/"+ requestBody.getObject().getEmail())
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("email", equalTo(requestBody.getObject().getEmail()));
    }

    @Test
    public void getByFirstName()  {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/account/getByFirstName/" + requestBody.getObject().getFirstName())
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("firstName", equalTo(requestBody.getObject().getFirstName()));
    }

    @Test
    public void getByLastName()  {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/account/getByLastName/"+ requestBody.getObject().getLastName())
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("lastName", equalTo(requestBody.getObject().getLastName()));
    }

    @Test
    public void getByPartialEmail()  {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/accounts/getByPartialEmail/"+ requestBody.getObject().getEmail().substring(0, requestBody.getObject().getEmail().length()/2))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void getByPartialFirstName()  {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/accounts/getByPartialFirstName/"+ requestBody.getObject().getFirstName().substring(0, requestBody.getObject().getFirstName().length()/2))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void getByPartialLastName()  {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/accounts/getByPartialLastName/"+ requestBody.getObject().getLastName().substring(0, requestBody.getObject().getLastName().length()/2))
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}