package com.revature.networkingassistant.controllers.AccountController;

import com.revature.networkingassistant.ControllerUtil;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.services.AccountServices.UpdateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = ControllerUtil.CORS_ALLOW)
public class UpdateAccountController {
    private UpdateAccountService updateAccountService;

    public UpdateAccountController() {}

    @Autowired
    public UpdateAccountController(UpdateAccountService updateAccountService) {
        this.updateAccountService = updateAccountService;
    }

    @RequestMapping(value = "/api/account/update", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Account> updateAccount(@RequestBody JsonRequestBody<Account> requestBody) {
        return updateAccountService.updateAccount(requestBody);
    }
}
