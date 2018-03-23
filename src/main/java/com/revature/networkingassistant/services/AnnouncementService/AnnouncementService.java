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

    private SessionTokenRepo sessionTokenRepo;
    private AnnouncementRepo announcementRepo;
    private AccountRepo accountRepo;

    public AnnouncementService() {}

    @Autowired
    public AnnouncementService(SessionTokenRepo sessionTokenRepo, AnnouncementRepo announcementRepo, AccountRepo accountRepo) {
        this.sessionTokenRepo = sessionTokenRepo;
        this.announcementRepo = announcementRepo;
        this.accountRepo = accountRepo;
    }

    @Transactional
    public ResponseEntity<Announcement> makeAnnouncement(JsonRequestBody<Announcement> requestBody) {
        SessionToken token = requestBody.getToken();
        if (sessionTokenRepo.existsById(token.getId())) {
            if (accountRepo.existsById(requestBody.getObject().getId())) {
                return new ResponseEntity<>(announcementRepo.save(requestBody.getObject()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Announcement(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new Announcement(), HttpStatus.UNAUTHORIZED);
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
