package com.revature.networkingassistant.services.EventServices;

import com.revature.networkingassistant.beans.Event;
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
    public ResponseEntity<Optional<Event>> findEvent(JsonRequestBody requestBody,int id) {
        SessionToken token = requestBody.getToken();
        if (tokenRepo.existsById(token.getId())) {
            Optional<Event> event = eventRepo.findById(id);
            if (event.isPresent()) {
                return new ResponseEntity<>(event, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(event, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.UNAUTHORIZED);
        }
    }
}
