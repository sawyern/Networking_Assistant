package com.revature.networkingassistant.services.AnnouncementService;

import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.AnnouncementRepo;
import com.revature.networkingassistant.repositories.SessionTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class AnnouncementService {

    @Autowired
    AnnouncementRepo announcementRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    SessionTokenRepo tokenRepo;

    public ResponseEntity<Announcement> makeAnnouncement(JsonRequestBody requestBody, int announcerId, int eventId) {
        SessionToken token = requestBody.getToken();
        if (tokenRepo.existsById(token.getId())) {
            if (accountRepo.existsById(announcerId)) {
                Announcement announcement = new Announcement(eventId, announcerId, (String) requestBody.getObject());
                return new ResponseEntity<>(announcementRepo.save(announcement), HttpStatus.OK);
            }
            return new ResponseEntity<>((Announcement)null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>((Announcement)null, HttpStatus.UNAUTHORIZED);
    }

    @Transactional
    public ResponseEntity<ArrayList<Announcement>> getByEventId(int id) {
        ArrayList<Announcement> announcements = announcementRepo.findByEventId(id);
        if (announcements.size() > 0) {
            return new ResponseEntity<>(announcements, HttpStatus.OK);
        } else return new ResponseEntity<>(announcements, HttpStatus.NO_CONTENT);
    }

    @Transactional
    public ResponseEntity<ArrayList<Announcement>> getByAccountId(int id) {
        ArrayList<Announcement> announcements = announcementRepo.findByAccountId(id);
        if (announcements.size() > 0) {
            return new ResponseEntity<>(announcements, HttpStatus.OK);
        } else return new ResponseEntity<>(announcements, HttpStatus.NO_CONTENT);
    }
}
