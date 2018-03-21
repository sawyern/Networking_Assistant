package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.EventServices.CreateEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class CreateEventController {

    @Autowired
    private CreateEventService eventService;

    @Transactional
    @RequestMapping(path = "/api/event/create", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(@RequestBody JsonRequestBody<Event> requestBody) {
        return eventService.createEvent(requestBody);
    }
}
