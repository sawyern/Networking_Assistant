package com.revature.networkingassistant.controllers.AnnouncementController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.beans.Event.Event;
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

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
@WebAppConfiguration
public class AnnouncementControllerTest {

    @Autowired
    private TestUtil testUtil;

    private JsonRequestBody<Announcement> requestBody = new JsonRequestBody<>();
    private Event testEvent;
    private Account testAccount;
    private ObjectMapper mapper;

    @Before
    public void setup() {
        testAccount = testUtil.createTestAccount();
        testEvent = testUtil.createNewEvent();
        mapper = new ObjectMapper();

        Announcement announcement = new Announcement();
        announcement.setAccountId(testAccount.getId());
        announcement.setEventId(testEvent.getId());
        announcement.setMessage("test message");

        requestBody.setObject(announcement);
        requestBody.setToken(testUtil.loginTestUser(testAccount));
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(testAccount);
        testUtil.removeTestEvent(testEvent);
        testUtil.removeSessionToken(requestBody.getToken());
        testUtil.removeAnnouncement(requestBody.getObject());
    }

    @Test
    public void makeAnnouncementTest() throws IOException {
        requestBody.getObject().setId(given()
                .body(mapper.writeValueAsString(requestBody))
                .contentType(ContentType.JSON)
                .when()
                .put("/api/announcement/create")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().path("id"));
    }
}
