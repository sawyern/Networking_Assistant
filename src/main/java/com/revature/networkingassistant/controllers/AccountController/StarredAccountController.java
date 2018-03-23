package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.StarService.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class StarredAccountController {
    private StarService starService;

    public StarredAccountController() {}

    @Autowired
    public StarredAccountController(StarService starService) {
        this.starService = starService;
    }

    @RequestMapping(path = "/api/starred/starAccount", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> starAccount(@RequestBody JsonRequestBody<Account> requestBody) {
        return starService.starAccount(requestBody);
    }

    @RequestMapping(path = "/api/starred/getStarredAccounts", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ArrayList<Account>> getStarredAccounts(@RequestBody JsonRequestBody requestBody) {
        return starService.getStarredAccounts(requestBody);
    }

    @RequestMapping(path = "/api/starred/deleteStarredAccount", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> deleteStarredAccount(@RequestBody JsonRequestBody<Account> requestBody) {
        return starService.deleteStarredAccount(requestBody);
    }

}
