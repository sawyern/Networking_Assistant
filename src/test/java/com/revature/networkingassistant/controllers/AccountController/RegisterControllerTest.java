package com.revature.networkingassistant.controllers.AccountController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
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
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class RegisterControllerTest {

    private JsonRequestBody<Account> requestBody;
    private Account testAccount;

    @Autowired
    private TestUtil testUtil;

    @Before
    public void setup() {
        Account account = new Account();
        account.setEmail("test@test.com");
        account.setPasswordHash("password");
        account.setFirstName("First");
        account.setLastName("Last");
        account.setBackground("Test background info.");
        account.setPhone("808-222-2222");
        account.setZipCode("96813");
        account.setAttachment(new byte[0]);

        requestBody = new JsonRequestBody<>();
        testAccount = account;
        requestBody.setObject(testAccount);
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(testAccount);
    }

    @Test
    public void registerAccountTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        given()
            .body(mapper.writeValueAsString(requestBody))
            .contentType(ContentType.JSON)
        .when()
            .put("/api/register")
        .then()
            .statusCode(HttpStatus.SC_CREATED)
            .assertThat().body("email", equalTo(testAccount.getEmail()))
            .assertThat().body("firstName", equalTo(testAccount.getFirstName()))
            .assertThat().body("lastName", equalTo(testAccount.getLastName()))
            .assertThat().body("phone", equalTo(testAccount.getPhone()))
            .assertThat().body("background", equalTo(testAccount.getBackground()));
    }

}