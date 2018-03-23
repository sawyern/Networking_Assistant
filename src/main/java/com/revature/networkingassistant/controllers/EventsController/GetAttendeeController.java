package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.services.AttendeeService.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GetAttendeeController {

    @Autowired
    AttendeeService attendeeService;

    @RequestMapping(path = "/api/event/getAttendees/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Attendant>> getAttendees(@PathVariable int eventId) {
        return attendeeService.getAttendees(eventId);
    }
}
