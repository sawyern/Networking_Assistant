package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.repositories.InviteRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InviteController {

    @Autowired
    SessionTokenRepo tokenRepo;

    @Autowired
    InviteRepo inviteRepo;

    @RequestMapping(path = "/api/send-invite/{session-token}/{eventId}/{accountId}")
    public void sendInvite(@RequestParam("session-token")SessionToken token,
                           @RequestParam("accountId") int accountId,
                           @RequestParam("eventId") int eventId) {
        if (tokenRepo.existsById(token.getId())) {
            Invite invite = new Invite(eventId, accountId);
            inviteRepo.save(invite);
        }
    }
}
