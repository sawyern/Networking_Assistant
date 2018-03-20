package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.SessionToken;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.AccountServices.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class LoginController {

    private LoginService loginService;

    public LoginController() { }

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SessionToken> verifyCredentials(@RequestBody JsonRequestBody<Account> jsonRequestBody) {
        return loginService.verifyCredentials(jsonRequestBody);
    }
}
