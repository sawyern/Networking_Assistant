package com.revature.networkingassistant.controllers.AnnouncementControllers;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Announcement;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.AnnouncementService.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class AnnouncementController {

    private AnnouncementService announcementService;

    public AnnouncementController(){}

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @RequestMapping(path = "/api/announcement/create", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Announcement> makeAnnouncement(@RequestBody JsonRequestBody<Announcement> requestBody) {
        return announcementService.makeAnnouncement(requestBody);
    }

    @RequestMapping(path = "/api/announcements/getByEventId/{eventId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Announcement>> getByEventId(@PathVariable int eventId) {
        return announcementService.getByEventId(eventId);
    }

    @RequestMapping(path = "/api/announcements/getByAccountId/{accountId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Announcement>> getByAccountId(@PathVariable int accountId) {
        return announcementService.getByAccountId(accountId);
    }
}
