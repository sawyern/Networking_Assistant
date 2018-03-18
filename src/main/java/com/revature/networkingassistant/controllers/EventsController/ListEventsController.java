package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.EventServices.ListEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ListEventsController {

    @Autowired
    private ListEventsService eventsService;

    @Transactional
    @RequestMapping(path = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Event>> listEvents(@RequestBody JsonRequestBody requestBody) {
        return eventsService.listEvents(requestBody);
    }
}
