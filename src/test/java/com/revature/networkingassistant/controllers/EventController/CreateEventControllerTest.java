package com.revature.networkingassistant.controllers.EventController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.Event.Location;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.controllers.TestUtil;
import com.revature.networkingassistant.repositories.EventRepo;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class CreateEventControllerTest {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private TestUtil testUtil;

    private JsonRequestBody<Event> requestBody;
    private ObjectMapper mapper;
    private Account testAccount;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        testAccount = testUtil.createTestAccount();

        //create test token
        SessionToken token = testUtil.loginTestUser(testAccount);

        //create test event
        Event testEvent = new Event();
        testEvent.setName("test event");
        testEvent.setDate(new Date());
        testEvent.setLocation(new Location());

        //create new body
        requestBody = new JsonRequestBody<>(token, testEvent);
    }

    @After
    public void rollback() throws IOException {
        testUtil.removeTestAccount(testAccount);
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
