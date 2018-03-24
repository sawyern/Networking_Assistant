package com.revature.networkingassistant.controllers.AnnouncementController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.controllers.TestUtil;
import com.revature.networkingassistant.repositories.AnnouncementRepo;
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
public class GetAnnouncementController {

    @Autowired
    private TestUtil testUtil;

    private JsonRequestBody<Announcement> requestBody = new JsonRequestBody<>();
    private Event testEvent;
    private Account testAccount;
    private Announcement announcement;
    private ObjectMapper mapper;

    @Before
    public void setup() {
        testAccount = testUtil.createTestAccount();
        testEvent = testUtil.createNewEvent();
        mapper = new ObjectMapper();

        announcement = testUtil.createTestAnnouncement(testEvent, testAccount);

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
    public void getByEventIdTest() {
        given()
                .when()
                .get("/api/announcements/getByEventId/" + requestBody.getObject().getEventId())
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getByAccountIdTest() throws IOException {
        given()
                .when()
                .get("/api/announcements/getByAccountId/" + requestBody.getToken().getAccountId())
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

}
