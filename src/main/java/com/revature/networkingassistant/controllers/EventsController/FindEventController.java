package com.revature.networkingassistant.controllers.EventsController;

import com.revature.networkingassistant.beans.Event;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class FindEventController {

    private EventRepo eventRepo;
    private SessionTokenRepo tokenRepo;

    @Autowired
    public FindEventController(EventRepo eventRepo, SessionTokenRepo tokenRepo) {
        this.eventRepo = eventRepo;
        this.tokenRepo = tokenRepo;
    }

    @Transactional
    @RequestMapping(path = "/api/find-event/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Event> findEvent(@RequestBody JsonRequestBody requestBody,
                                     @PathVariable("id") int id,
                                     HttpServletResponse response) {
        SessionToken token = requestBody.getToken();
        if (tokenRepo.existsById(token.getId())) {
            Optional<Event> event = eventRepo.findById(id);
            if (event.isPresent()) {
                return event;
            } else {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return event;
            }
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return Optional.empty();
        }
    }
}
