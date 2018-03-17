package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ListEventsController {

    private EventRepo eventRepo;
    private SessionTokenRepo tokenRepo;

    @Autowired
    public ListEventsController(EventRepo eventRepo, SessionTokenRepo tokenRepo) {
        this.eventRepo = eventRepo;
        this.tokenRepo = tokenRepo;
    }

    @Transactional
    @RequestMapping(path = "/api/list-events/{session-token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Event> listEvents(@RequestBody JsonRequestBody requestBody, HttpServletResponse response) {
        SessionToken token = requestBody.getToken();
        if (tokenRepo.existsById(token.getId())) {
            return eventRepo.findAll();
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
    }
}
