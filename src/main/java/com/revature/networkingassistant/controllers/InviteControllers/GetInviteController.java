package com.revature.networkingassistant.controllers.InviteControllers;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.InviteServices.GetInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class GetInviteController {

    private GetInviteService getInviteService;

    public GetInviteController() {}

    @Autowired
    public GetInviteController(GetInviteService getInviteService) {
        this.getInviteService = getInviteService;
    }

    @RequestMapping(path = "/api/invites/getSentInvites", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Invite>> getSentInvites(@RequestBody JsonRequestBody<Invite> requestBody) {
        return getInviteService.getSentInvites(requestBody);
    }

    @RequestMapping(path = "/api/invites/getReceivedInvites", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Invite>> getReceivedInvites(@RequestBody JsonRequestBody<Invite> requestBody) {
        return getInviteService.getRecievedInvites(requestBody);
    }
}
