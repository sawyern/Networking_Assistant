package com.revature.networkingassistant.controllers.AttendantControllers;

import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.services.AttendantService.AttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AttendantController {

    @Autowired
    AttendantService attendantService;

    @RequestMapping(path = "/api/attendants/getByEventId/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Attendant>> getEventAttendants(@PathVariable("eventId") int eventId) {
        return attendantService.getAttendees(eventId);
    }

    @RequestMapping(path = "/api/events/getAttendantEvents/{attendantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Attendant>> getAttendantEvents(@PathVariable("attendantId") int eventId) {
        return attendantService.getAttendees(eventId);
    }
}
