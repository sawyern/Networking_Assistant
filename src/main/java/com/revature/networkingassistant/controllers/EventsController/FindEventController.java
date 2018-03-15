package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class FindEventController {

    @Autowired
    EventRepo eventRepo;

    @Autowired
    SessionTokenRepo tokenRepo;

    @RequestMapping(path = "/api/find-event/{session-token}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Event> findEvent(@PathVariable("session-token")SessionToken token,
                                     @PathVariable("id") int id,
                                     HttpServletResponse response) {
        if (tokenRepo.existsById(token.getId())) {
            Optional<Event> event = eventRepo.findById(id);
            if (event.isPresent()) {
                return event;
            } else {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return event;
            }
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
    }
}