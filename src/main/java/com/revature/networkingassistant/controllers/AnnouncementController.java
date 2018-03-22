package com.revature.networkingassistant.controllers;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.AnnouncementService.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class AnnouncementController {

    private AnnouncementService announcementService;

    public AnnouncementController() {}

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @RequestMapping(path = "/api/announcement/create", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Announcement> makeAnnouncement(@RequestBody JsonRequestBody<Announcement> requestBody) {
        return announcementService.makeAnnouncement(requestBody);
    }
}
