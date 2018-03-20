package com.revature.networkingassistant.controllers.EventController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.Event.Location;
import com.revature.networkingassistant.beans.Event.State;
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
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.put;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class CreateEventControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

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

        Location loc = new Location();
        loc.setEvent(testEvent);
        loc.setAddressNum("1234");
        loc.setStreetName("oak street");
        loc.setCity("San Francisco");
        loc.setState(State.CA);
        loc.setZip("95050");
        testEvent.setLocation(loc);

        //create new body
        requestBody = new JsonRequestBody<>(token, testEvent);
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(testAccount);
        testUtil.removeTestEvent(requestBody.getObject());
    }

    @Test
    public void createEventTest() throws Exception {
        given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
            .when()
                .put("/api/event/create")
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat().body("location.addressNum", equalTo(requestBody.getObject().getLocation().getAddressNum()))
                .assertThat().body("location.streetName", equalTo(requestBody.getObject().getLocation().getStreetName()))
                .assertThat().body("location.city", equalTo(requestBody.getObject().getLocation().getCity()))
                .assertThat().body("location.state", equalTo(requestBody.getObject().getLocation().getState().toString()))
                .assertThat().body("location.zip", equalTo(requestBody.getObject().getLocation().getZip()))
                //.assertThat().body("date", equalTo(requestBody.getObject().getDate().toString()))
                .assertThat().body("name", equalTo(requestBody.getObject().getName()));
    }
}
