package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.InviteRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Which package should this go in?

@RestController
public class InviteController {

    private SessionTokenRepo tokenRepo;
    private InviteRepo inviteRepo;

    @Autowired
    public InviteController(SessionTokenRepo tokenRepo, InviteRepo inviteRepo) {
        this.tokenRepo = tokenRepo;
        this.inviteRepo = inviteRepo;
    }

    @RequestMapping(path = "/api/send-invite/{eventId}/{fromId}/{toId}")
    public void sendInvite(@RequestBody JsonRequestBody requestBody,
                           @PathVariable("eventId") int eventId,
                           @PathVariable("fromId") int fromId,
                           @PathVariable("toId") int toId) {
        SessionToken token = requestBody.getToken();
        if (tokenRepo.existsById(token.getId())) {
            Invite invite = new Invite(eventId, fromId, toId);
            inviteRepo.save(invite);
        }
    }
}
