package com.revature.networkingassistant.services.EventServices;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.beans.Attendant.Role;
import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.AttendantRepo;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
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

    @Autowired
    public CreateEventService(EventRepo eventRepo, AccountRepo accountRepo, SessionTokenRepo tokenRepo, AttendantRepo attendantRepo) {
        this.eventRepo = eventRepo;
        this.accountRepo = accountRepo;
        this.tokenRepo = tokenRepo;
        this.attendantRepo = attendantRepo;
    }

    @Transactional
    public ResponseEntity<Event> createEvent(JsonRequestBody<Event> requestBody) {
        SessionToken token = requestBody.getToken();
        Optional<Account> optional = accountRepo.findById(token.getAccountId());
        //verify token
        if (tokenRepo.existsById(token.getId()) && optional.isPresent()) {
            //get form information
            Event toSave = requestBody.getObject();
            //insert into events table
            Event event = eventRepo.save(toSave);
            //get ids for junction table
            int accountId = optional.get().getId();
            int eventId = event.getId();
            //insert into junction table
            Attendant attendant = new Attendant(eventId, accountId, Role.COORDINATOR);
            attendantRepo.save(attendant);
            //return newly created event
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Event)null, HttpStatus.UNAUTHORIZED);
        }
    }
}


