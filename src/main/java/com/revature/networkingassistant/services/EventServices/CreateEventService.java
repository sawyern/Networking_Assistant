package com.revature.networkingassistant.services.EventServices;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.beans.Attendant.Role;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateEventService {

    private EventRepo eventRepo;
    private AccountRepo accountRepo;
    private SessionTokenRepo tokenRepo;
    private AttendantRepo attendantRepo;
    private LocationRepo locationRepo;

    @Autowired
    public CreateEventService(EventRepo eventRepo, AccountRepo accountRepo, SessionTokenRepo tokenRepo, AttendantRepo attendantRepo, LocationRepo locationRepo) {
        this.eventRepo = eventRepo;
        this.accountRepo = accountRepo;
        this.tokenRepo = tokenRepo;
        this.attendantRepo = attendantRepo;
        this.locationRepo = locationRepo;
    }

    @Transactional
    public ResponseEntity<Event> createEvent(JsonRequestBody<Event> requestBody) {
        try {
            SessionToken token = requestBody.getToken();
            Optional<Account> optional = accountRepo.findById(token.getAccountId());
            //verify token
            if (tokenRepo.existsById(token.getId()) && optional.isPresent()) {
                //insert into events table
                Event event = eventRepo.save(requestBody.getObject());
                locationRepo.save(requestBody.getObject().getLocation());
                //get ids for junction table
                int accountId = optional.get().getId();
                int eventId = event.getId();
                //insert into junction table
                Attendant attendant = new Attendant(eventId, accountId, Role.COORDINATOR);
                attendantRepo.save(attendant);
                //return newly created event
                return new ResponseEntity<>(event, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new Event(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Event(), HttpStatus.BAD_GATEWAY);
        }
    }
}


