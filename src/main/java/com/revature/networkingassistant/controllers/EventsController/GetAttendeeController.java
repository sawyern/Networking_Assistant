package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.services.AttendeeService.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class GetAttendeeController {

    @Autowired
    AttendeeService attendeeService;

    @RequestMapping(path = "/api/event/getAttendees/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Attendant>> getAttendees(@PathVariable("id") int eventId) {
        return attendeeService.getAttendees(eventId);
    }
}
