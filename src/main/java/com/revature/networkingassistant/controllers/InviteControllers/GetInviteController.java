package com.revature.networkingassistant.controllers.InviteControllers;

import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.InviteServices.GetInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GetInviteController {

    @Autowired
    private GetInviteService inviteService;

    @RequestMapping(path = "/api/invites/getSentInvites", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Invite>> getSentInvites(@RequestBody JsonRequestBody<Invite> requestBody) {
        return inviteService.getSentInvites(requestBody);
    }

    @RequestMapping(path = "/api/invites/getReceivedInvites", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Invite>> getReceivedInvites(@RequestBody JsonRequestBody<Invite> requestBody) {
        return inviteService.getRecievedInvites(requestBody);
    }
}
