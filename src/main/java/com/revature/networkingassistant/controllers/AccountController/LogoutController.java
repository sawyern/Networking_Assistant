package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.AccountServices.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class LogoutController {

    private LogoutService logoutService;

    public LogoutController() { }

    @Autowired
    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @RequestMapping(value = "/api/logout", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> logout(@RequestBody JsonRequestBody<Account> jsonRequestBody) {
        return logoutService.logout(jsonRequestBody);
    }
}
