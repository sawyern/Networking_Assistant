package com.revature.networkingassistant.controllers.EventController;

import com.revature.networkingassistant.NetworkingAssistantApplication;
import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.controllers.EventsController.CreateEventController;
import com.revature.networkingassistant.repositories.EventRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CreateEventController.class)
@Transactional
public class CreateEventControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @MockBean
    private EventRepo eventRepo;


    @Mock
    private JsonRequestBody<Event> testBody;

    @Test
    public void createEventTest() throws Exception {
        given(eventRepo.findById(-1).get()).willReturn(testBody.getObject());
        Mockito.when(testBody.getToken()).thenReturn(testBody.getToken());


        mvc.perform(put("/api/event/create")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$[1].name", is("test event")));
    }

    @Before
    public void setup() {
        //create test token
        SessionToken token = new SessionToken();
        token.setAccountId(-1);
        token.setId("testToken");

        //create test event
        Event testEvent = new Event();
        testEvent.setId(-1);
        testEvent.setName("test event");
        testEvent.setDate(new Date());
        testEvent.setLocation("test loc");

        //create new body
        testBody = new JsonRequestBody<>(token, testEvent);
    }
}
