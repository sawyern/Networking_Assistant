package com.revature.networkingassistant.controllers.InviteController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.revature.networkingassistant.AppConfig;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.Invite;
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
public class GetInviteControllerTest {

    @Autowired
    private TestUtil testUtil;

    private JsonRequestBody<Invite> requestBody = new JsonRequestBody<>();
    private Event testEvent;
    private Account testAccount;
    private Account testAccount2;
    private ObjectMapper mapper;

    @Before
    public void setup() {
        testAccount = testUtil.createTestAccount();
        testAccount2 = testUtil.createTestAccount("test2@test.com");
        testEvent = testUtil.createNewEvent();
        mapper = new ObjectMapper();

        Invite invite = new Invite();
        invite.setEventId(testEvent.getId());
        invite.setInviter(testAccount.getId());
        invite.setInvitee(testAccount2.getId());

        requestBody.setObject(invite);
        requestBody.setToken(testUtil.loginTestUser(testAccount));
    }

    @After
    public void rollback() {
        testUtil.removeTestAccount(testAccount);
        testUtil.removeTestEvent(testEvent);
        testUtil.removeSessionToken(requestBody.getToken());
        testUtil.removeInvitation(requestBody.getObject());
    }

    @Test
    public void getSentInvites() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/api/invites/getSentInvites")
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat().body("$[0].inviter", equalTo(testAccount.getId()));
    }

}
