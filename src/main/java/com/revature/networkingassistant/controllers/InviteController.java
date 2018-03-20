package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.InviteServices.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InviteController {
    private InviteService inviteService;

    public InviteController() {}

    @Autowired
    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @RequestMapping(path = "/api/event/invite")
    public ResponseEntity<Invite> sendInvite(@RequestBody JsonRequestBody<Invite> requestBody) {
        return inviteService.sendInvite(requestBody);
    }
}
