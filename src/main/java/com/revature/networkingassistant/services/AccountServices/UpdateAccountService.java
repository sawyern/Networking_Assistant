package com.revature.networkingassistant.services.AccountServices;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateAccountService {

    private AccountRepo accountRepo;

    public UpdateAccountService() {}

    @Autowired
    public UpdateAccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public ResponseEntity<Account> updateAccount(JsonRequestBody<Account> requestBody) {
        if (accountRepo.existsById(requestBody.getObject().getId())) {
            if (requestBody.getToken().getAccountId() == requestBody.getObject().getId()) {
                if (accountRepo.findById(requestBody.getObject().getId()).equals(requestBody.getObject()))
                    return new ResponseEntity<>(requestBody.getObject(), HttpStatus.OK);
                else return new ResponseEntity<>(accountRepo.save(requestBody.getObject()), HttpStatus.ACCEPTED);
            } else return new ResponseEntity<>(new Account(), HttpStatus.UNAUTHORIZED);
        } else return new ResponseEntity<>(new Account(), HttpStatus.BAD_GATEWAY);
    }
}
