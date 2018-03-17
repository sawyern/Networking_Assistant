package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.AnnouncementRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnouncementController {

    @Autowired
    SessionTokenRepo tokenRepo;

    @Autowired
    AnnouncementRepo announcementRepo;

    @Autowired
    AccountRepo accountRepo;

    @RequestMapping(path = "/api/make-announcement/{announcerId]/{eventId}")
    public ResponseEntity<Announcement> makeAnnouncement(@RequestBody JsonRequestBody<String> requestBody,
                                                   @PathVariable("announcerId") int announcerId,
                                                   @PathVariable("eventID") int eventId) {
        SessionToken token = requestBody.getToken();
        if (tokenRepo.existsById(token.getId())) {
            if (accountRepo.existsById(announcerId)) {
                Announcement announcement = new Announcement(eventId, announcerId, requestBody.getObject());
                return new ResponseEntity<>(announcementRepo.save(announcement), HttpStatus.OK);
            }
            return new ResponseEntity<>((Announcement)null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>((Announcement)null, HttpStatus.UNAUTHORIZED);
    }
}
