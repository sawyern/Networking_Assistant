package com.revature.networkingassistant.controllers.EventController;


import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Event.Event;
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
public class GetEventTest {
    @Autowired
    private TestUtil testUtil;

    @Autowired
    private SessionTokenRepo sessionTokenRepo;

    private Account testAccount;
    private Event testEvent;

    @Before
    public void setup() {
        testAccount = testUtil.createTestAccount();
        testEvent = testUtil.createNewEvent();
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(testAccount);
        testUtil.removeTestEvent(testEvent);
    }

    @Test
    public void testGetEvent() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/event/getEvent/"+ testEvent.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("id", equalTo(testEvent.getId()));
    }

    @Test
    public void testGetAll() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/events/getAll")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
