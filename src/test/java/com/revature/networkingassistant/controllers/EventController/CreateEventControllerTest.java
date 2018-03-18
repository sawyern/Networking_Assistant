package com.revature.networkingassistant.controllers.EventController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import com.revature.networkingassistant.services.AccountServices.RegisterService;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class CreateEventControllerTest {

    @Autowired
    private EventRepo eventRepo;

    private JsonRequestBody<Event> requestBody;
    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();

        //create test token
        SessionToken token = new SessionToken();
        token.setAccountId(-1);
        token.setId("testToken");

        //create test event
        Event testEvent = new Event();
        testEvent.setName("test event");
        testEvent.setDate(new Date());
        testEvent.setLocation("test loc");

        //create new body
        requestBody = new JsonRequestBody<>(token, testEvent);
    }

    @After
    public void rollback() throws IOException {
    }


    @Test
    public void createEventTest() throws Exception {
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/event/create")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        //.assertThat().body()
    }
}
