package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.AnnouncementRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import com.revature.networkingassistant.services.AnnouncementService.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AnnouncementController {

    @Autowired
    SessionTokenRepo tokenRepo;

    @Autowired
    AnnouncementRepo announcementRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    AnnouncementService announcementService;

    @RequestMapping(path = "/api/makeAnnouncement/{announcerId]/{eventId}",  method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Announcement> makeAnnouncement(@RequestBody JsonRequestBody<String> requestBody,
                                                   @PathVariable("announcerId") int announcerId,
                                                   @PathVariable("eventID") int eventId) {
        return announcementService.makeAnnouncement(requestBody, announcerId, eventId);
    }

    @RequestMapping(path = "/api/announcements/geyByEventId/{eventId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Announcement>> getAnnouncementsByEventId(@PathVariable("eventId") int eventId) {
        return announcementService.getByEventId(eventId);
    }

    @RequestMapping(path = "/api/announcements/getByAnnouncerId/{announcerId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Announcement>> getAnnouncementsByAnnouncerId(@PathVariable("announcerId") int announcerId) {
        return announcementService.getByAccountId(announcerId);
    }
}
