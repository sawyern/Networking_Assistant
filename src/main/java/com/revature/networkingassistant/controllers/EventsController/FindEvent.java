package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class FindEvent {

    @Autowired
    EventRepo eventRepo;

    @RequestMapping(path = "/api/find-event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Event> findEvent(@PathVariable("id") int id, HttpServletResponse response) {
        Optional<Event> event = eventRepo.findById(id);
        if (event.isPresent()) {
            return event;
        } else {
            response.setStatus(500);
            return event;
        }
    }
}
