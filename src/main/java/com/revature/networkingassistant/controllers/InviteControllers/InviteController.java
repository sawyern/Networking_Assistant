package com.revature.networkingassistant.controllers.InviteControllers;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Invite;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.InviteServices.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class InviteController {
    private InviteService inviteService;

    public InviteController() {}

    @Autowired
    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @RequestMapping(path = "/api/event/invite", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Invite> sendInvite(@RequestBody JsonRequestBody<Invite> requestBody) {
        return inviteService.sendInvite(requestBody);
    }

    @RequestMapping(path = "/api/account/acceptInvite", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity acceptInvite(@RequestBody JsonRequestBody<Invite> requestBody) {
        return inviteService.acceptInvite(requestBody);
    }

    @RequestMapping(path = "/api/account/ignoreInvite", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity ignoreInvite(@RequestBody JsonRequestBody<Invite> requestBody) {
        return inviteService.ignoreInvite(requestBody);
    }
}
