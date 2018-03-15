package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.InviteRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InviteController {

    @Autowired
    SessionTokenRepo tokenRepo;

    @Autowired
    InviteRepo inviteRepo;

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
