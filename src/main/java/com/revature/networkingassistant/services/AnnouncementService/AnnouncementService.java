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
import org.springframework.web.bind.annotation.RequestBody;

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

    public ResponseEntity<Announcement> createAnnouncement(@RequestBody JsonRequestBody<Announcement> requestBody) {
        try {
            SessionToken token = requestBody.getToken();
            if (sessionTokenRepo.existsById(token.getId())) {
                if (accountRepo.existsById(requestBody.getObject().getAccountId())) {
                    Announcement announcement = new Announcement(requestBody.getObject().getEventId(), requestBody.getObject().getAccountId(), requestBody.getObject().getMessage());
                    return new ResponseEntity<>(announcementRepo.save(announcement), HttpStatus.OK);
                }
                return new ResponseEntity<>(new Announcement(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new Announcement(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Announcement(), HttpStatus.BAD_GATEWAY);
        }
    }
}
