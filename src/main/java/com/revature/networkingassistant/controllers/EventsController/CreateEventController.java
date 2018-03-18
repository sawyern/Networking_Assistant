package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.beans.Attendant.Role;
import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.AttendantRepo;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import com.revature.networkingassistant.services.EventServices.CreateEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class CreateEventController {

    @Autowired
    private CreateEventService eventService;

    @Transactional
    @RequestMapping(path = "/api/event/create", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(@RequestBody JsonRequestBody<Event> requestBody) {
        return eventService.createEvent(requestBody);
    }
}
