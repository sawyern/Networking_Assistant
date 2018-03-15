package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Attendant;
import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.RequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.AttendantRepo;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class CreateEventController {

    @Autowired
    EventRepo eventRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    SessionTokenRepo tokenRepo;

    @Autowired
    AttendantRepo attendantRepo;

    @Transactional
    @RequestMapping(path = "/api/create-event", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event createEvent(@org.springframework.web.bind.annotation.RequestBody RequestBody<Event> requestBody,
                             HttpServletResponse response) {
        SessionToken token = requestBody.getToken();
        Optional<Account> optional = accountRepo.findById(token.getAccountId());
        //verify token and account
        if (tokenRepo.existsById(token.getId()) && optional.isPresent()) {
            //get form information
            Event toSave = requestBody.getObject();
            //insert into events table
            Event event = eventRepo.save(toSave);
            //get ids for junction table
            int accountId = optional.get().getId();
            int eventId = event.getId();
            //insert into junction table
            Attendant attendant = new Attendant(eventId, accountId, "Coordinator");
            attendantRepo.save(attendant);
            //return newly created event
            return event;
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }
    }
}
