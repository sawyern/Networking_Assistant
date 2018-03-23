package com.revature.networkingassistant.controllers.AttendantControllers;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.beans.Event.Event;
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

    AttendantService attendantService;

    public AttendantController() {}

    @Autowired
    public AttendantController(AttendantService attendantService) {
        this.attendantService = attendantService;
    }

    @RequestMapping(path = "/api/attendants/getByEventId/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Attendant>> getEventAttendants(@PathVariable("eventId") int eventId) {
        return attendantService.getAttendees(eventId);
    }

    @RequestMapping(path = "/api/attendant/getEvents/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Event>> getAttendantEvents(@PathVariable("accountId") int accountId) {
        return attendantService.getEvents(accountId);
    }

    @RequestMapping(path = "/api/events/getEventStarred/{eventId}/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Account>> getEventStarred(@PathVariable("eventId") int eventId, @PathVariable("accountID") int accountId) {
        return attendantService.getEventStarred(eventId, accountId);
    }
}
