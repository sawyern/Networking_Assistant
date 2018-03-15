package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ListEventsController {

    @Autowired
    EventRepo eventRepo;

    @Autowired
    SessionTokenRepo tokenRepo;

    @Transactional
    @RequestMapping(path = "/api/list-events/{session-token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Event> listEvents(@PathVariable("session-token")SessionToken token, HttpServletResponse response) {
        if (tokenRepo.existsById(token.getId())) {
            return eventRepo.findAll();
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
    }
}
