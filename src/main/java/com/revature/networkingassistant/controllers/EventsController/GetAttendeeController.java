package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.services.AttendantService.AttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class GetAttendeeController {

    @Autowired
    AttendantService attendeeService;

    @RequestMapping(path = "/api/event/getAttendees/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Attendant>> getAttendees(@PathVariable int eventId) {
        return attendeeService.getAttendees(eventId);
    }
}
