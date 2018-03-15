package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.RequestBody;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CreateEventController {

    @Autowired
    EventRepo eventRepo;

    @Autowired
    SessionTokenRepo tokenRepo;

    @RequestMapping(path = "/api/create-event/{session-token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event createEvent(@RequestParam("session-token")SessionToken token,
                             @org.springframework.web.bind.annotation.RequestBody RequestBody<Event> requestBody,
                             HttpServletResponse response) {
        if (tokenRepo.existsById(token.getId())) {
            Event toSave = requestBody.getObject();
            eventRepo.save(toSave);
            return toSave;
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
    }
}
