package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListEventsController {

    @Autowired
    EventRepo eventRepo;

    @RequestMapping(path = "/api/list-events", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Event> listEvents() {
        return eventRepo.findAll();
    }
}
