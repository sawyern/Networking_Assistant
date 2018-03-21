package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.services.EventServices.GetEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class GetEventController {

    @Autowired
    private GetEventService eventService;

    @RequestMapping(path = "/api/events/getAll", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Event>> getAllEvents() {
        return eventService.listEvents();
    }

    @RequestMapping(path = "/api/event/getEvent/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Event>> getEvent(@PathVariable(name = "id") int id) {
        return eventService.findEventById(id);
    }

    @RequestMapping(path = "/api/events/getByName/{name}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Event>> getEventsByName(@PathVariable(name = "name") String name) {
        return eventService.findEventsByName(name);
    }

    @RequestMapping(path = "/api/events/getByLocation/{location}", method = RequestMethod.GET)
    private ResponseEntity<ArrayList<Event>> getEventsByLocation(@PathVariable(name = "location") String location) {
        return eventService.findEventsByLocation(location);
    }

    @RequestMapping(path = "/api/events/getByLocation/{date}", method = RequestMethod.GET)
    private ResponseEntity<ArrayList<Event>> getEventsByDate(@PathVariable(name = "date") String date) {
        return eventService.findEventsByDate(date);
    }
}
