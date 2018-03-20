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

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<Event>> listEvents(JsonRequestBody requestBody) {
        try {
            SessionToken token = requestBody.getToken();
            if (tokenRepo.existsById(token.getId())) {
                return new ResponseEntity<>(eventRepo.findAll(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<Event>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Event>(), HttpStatus.BAD_GATEWAY);
        }
    }
}
