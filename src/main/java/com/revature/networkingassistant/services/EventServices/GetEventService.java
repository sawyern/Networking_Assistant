package com.revature.networkingassistant.services.EventServices;

import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.Event.Location;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
public class GetEventService {

    private EventRepo eventRepo;

    public GetEventService() {}

    @Autowired
    public GetEventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Transactional
    public ResponseEntity<ArrayList<Event>> listEvents() {
        ArrayList<Event> events = eventRepo.findAll();
        if (events.size() > 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else return new ResponseEntity<>(new ArrayList<Event>(), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Event> findEventById(int id) {
        Optional<Event> event = eventRepo.findById(id);
        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Event(), HttpStatus.NO_CONTENT);
        }
    }

    @Transactional
    public ResponseEntity<ArrayList<Event>> findEventsByName(String name) {
        ArrayList<Event> events = eventRepo.findEventsByName(name);
        if (events.size() > 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else return new ResponseEntity<>(events, HttpStatus.NO_CONTENT);
    }

    @Transactional
    public ResponseEntity<ArrayList<Event>> findEventsByLocation (JsonRequestBody<Location> requestBody) {
        ArrayList<Event> events = eventRepo.findEventsByLocation(requestBody.getObject());
        if (events.size() > 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else return new ResponseEntity<>(events, HttpStatus.NO_CONTENT);
    }

    @Transactional
    public ResponseEntity<ArrayList<Event>> findEventsByDate (JsonRequestBody<Date> requestBody) {
        ArrayList<Event> events = eventRepo.findEventsByDate(requestBody.getObject());
        if (events.size() > 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else return new ResponseEntity<>(events, HttpStatus.NO_CONTENT);
    }
}
