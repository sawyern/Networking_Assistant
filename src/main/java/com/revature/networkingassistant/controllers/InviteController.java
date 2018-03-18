package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.EventRepo;
import com.revature.networkingassistant.repositories.InviteRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Which package should this go in?

@RestController
public class InviteController {

    @Autowired
    private SessionTokenRepo tokenRepo;

    @Autowired
    private InviteRepo inviteRepo;

    public InviteController() {}

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private EventRepo eventRepo;

    @RequestMapping(path = "/api/send-invite/{eventId}/{fromId}/{toId}")
    public ResponseEntity<Invite> sendInvite(@RequestBody JsonRequestBody requestBody,
                                     @PathVariable("eventId") int eventId,
                                     @PathVariable("fromId") int fromId,
                                     @PathVariable("toId") int toId) {
        SessionToken token = requestBody.getToken();
        if (tokenRepo.existsById(token.getId())) {
            if (accountRepo.existsById(fromId) && accountRepo.existsById(toId) && eventRepo.existsById(eventId)) {
                Invite invite = new Invite(eventId, fromId, toId);
                return new ResponseEntity<>(inviteRepo.save(invite), HttpStatus.OK);
            }
            return new ResponseEntity<>((Invite)null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>((Invite)null, HttpStatus.UNAUTHORIZED);
    }
}
