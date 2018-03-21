package com.revature.networkingassistant.services.EventServices;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GetEventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private SessionTokenRepo tokenRepo;

    @Transactional
    public ResponseEntity<ArrayList<Event>> listEvents() {
        ArrayList<Event> events = eventRepo.findAll();
        if (events.size() > 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else return new ResponseEntity<>(events, HttpStatus.NO_CONTENT);
    }

    @Transactional
    public ResponseEntity<Optional<Event>> findEventById(int id) {
        Optional<Event> event = eventRepo.findById(id);
        if (event.isPresent()) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(event, HttpStatus.NO_CONTENT);
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
    public ResponseEntity<ArrayList<Event>> findEventsByLocation (String location) {
        ArrayList<Event> events = eventRepo.findEventsByLocation(location);
        if (events.size() > 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else return new ResponseEntity<>(events, HttpStatus.NO_CONTENT);
    }

    @Transactional
    public ResponseEntity<ArrayList<Event>> findEventsByDate (String date) {
        ArrayList<Event> events = eventRepo.findEventsByDate(date);
        if (events.size() > 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else return new ResponseEntity<>(events, HttpStatus.NO_CONTENT);
    }
}
