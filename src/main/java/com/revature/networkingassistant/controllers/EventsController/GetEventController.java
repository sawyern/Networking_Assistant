package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.Event.Location;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.EventServices.GetEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class GetEventController {

    private GetEventService getEventService;

    public GetEventController() {

    }

    @Autowired
    public GetEventController(GetEventService getEventService) {
        this.getEventService = getEventService;
    }

    @RequestMapping(path = "/api/events/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Event>> getAllEvents() {
        return getEventService.listEvents();
    }

    @RequestMapping(path = "/api/event/getEvent/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEvent(@PathVariable(name = "id") int id) {
        return getEventService.findEventById(id);
    }

    @RequestMapping(path = "/api/events/getByName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Event>> getEventsByName(@PathVariable(name = "name") String name) {
        return getEventService.findEventsByName(name);
    }

    @RequestMapping(path = "/api/events/getByLocation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ArrayList<Event>> getEventsByLocation(@RequestBody JsonRequestBody<Location> requestBody) {
        return getEventService.findEventsByLocation(requestBody);
    }

    @RequestMapping(path = "/api/events/getByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ArrayList<Event>> getEventsByDate(@RequestBody JsonRequestBody<Date> requestBody) {
        return getEventService.findEventsByDate(requestBody);
    }
}
