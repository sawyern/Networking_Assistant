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

@Service
public class ListEventsService {

    private EventRepo eventRepo;
    private SessionTokenRepo tokenRepo;

    @Autowired
    public ListEventsService(EventRepo eventRepo, SessionTokenRepo tokenRepo) {
        this.eventRepo = eventRepo;
        this.tokenRepo = tokenRepo;
    }

    @Transactional
    public ResponseEntity<Iterable<Event>> listEvents(JsonRequestBody requestBody) {
        SessionToken token = requestBody.getToken();
        if (tokenRepo.existsById(token.getId())) {
            return new ResponseEntity<>(eventRepo.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Iterable<Event>)null, HttpStatus.UNAUTHORIZED);
        }
    }
}
