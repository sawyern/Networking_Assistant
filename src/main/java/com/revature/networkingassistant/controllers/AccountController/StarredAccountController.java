package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.services.StarService.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class StarredAccountController {

    @Autowired
    StarService starService;

    @RequestMapping(path = "/api/starred/starAccount/{ownerId}/{starredId}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> starAccount(@PathVariable("ownerId") int ownerId, @PathVariable("starredId") int starredId) {
        return starService.starAccount(ownerId, starredId);
    }

    @RequestMapping(path = "/api/starred/getStarredAccounts/{ownerId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Account>> getStarredAccounts(@PathVariable("ownerId") int ownerId) {
        return starService.getStarredAccounts(ownerId);
    }

    @RequestMapping(path = "/api/starred/getEventStarredAccounts/{eventId}/{ownerId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Account>> getEventStarredAccounts(@PathVariable("eventId") int eventId, @PathVariable("ownerId") int ownerId) {
        return starService.getEventStarredAccounts(eventId, ownerId);
    }
}
