package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.EventServices.FindEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FindEventController {

    @Autowired
    FindEventService eventService;

    @Transactional
    @RequestMapping(path = "/api/event/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Event>> findEvent(@RequestBody JsonRequestBody requestBody,
                                                    @PathVariable("id") int id) {
        return eventService.findEvent(requestBody, id);
    }
}
