package com.revature.networkingassistant.services.EventServices;

import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FindEventService {

    private EventRepo eventRepo;
    private SessionTokenRepo tokenRepo;

    @Autowired
    public FindEventService(EventRepo eventRepo, SessionTokenRepo tokenRepo) {
        this.eventRepo = eventRepo;
        this.tokenRepo = tokenRepo;
    }

    @Transactional
    public ResponseEntity<Event> findEvent(int id) {
        try {
            Optional<Event> event = eventRepo.findById(id);
            if (event.isPresent()) {
                return new ResponseEntity<>(event.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Event(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Event(), HttpStatus.BAD_GATEWAY);
        }
    }
}
