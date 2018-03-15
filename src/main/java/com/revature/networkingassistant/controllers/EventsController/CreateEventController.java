package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.controllers.DTO.RequestBody;
import com.revature.networkingassistant.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEventController {
    @Autowired
    EventRepo eventRepo;

    @RequestMapping(path = "/api/create-event", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event createEvent(@org.springframework.web.bind.annotation.RequestBody RequestBody<Event> requestBody) {
        Event toSave = requestBody.getObject();
        eventRepo.save(toSave);
        return toSave;
    }
}
