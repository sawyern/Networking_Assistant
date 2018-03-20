package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.AnnouncementService.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class AnnouncementController {

    private AnnouncementService announcementService;

    public AnnouncementController() {}

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @RequestMapping(path = "/api/announcement/create")
    public ResponseEntity<Announcement> makeAnnouncement(@RequestBody JsonRequestBody<Announcement> requestBody) {
        return announcementService.createAnnouncement(requestBody);
    }
}
