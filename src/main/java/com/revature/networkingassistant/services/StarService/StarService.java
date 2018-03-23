package com.revature.networkingassistant.services.StarService;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.JsonRequestBody;
import com.revature.networkingassistant.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StarService {

    private AccountRepo accountRepo;

    public StarService() {}

    @Autowired
    public StarService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Transactional
    public ResponseEntity<Account> starAccount(JsonRequestBody<Account> requestBody) {
        Optional<Account> ownerAccount = accountRepo.findById(requestBody.getToken().getAccountId());
        Optional<Account> starredAccount = accountRepo.findById(requestBody.getObject().getId());
        if (ownerAccount.isPresent()) {
            if (starredAccount.isPresent()) {
                ownerAccount.get().getMyStarredList().add(starredAccount.get());
                accountRepo.save(ownerAccount.get());
                return new ResponseEntity<>(starredAccount.get(), HttpStatus.CREATED);
            } return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<ArrayList<Account>> getStarredAccounts(JsonRequestBody requestBody) {
        Optional<Account> account = accountRepo.findById(requestBody.getToken().getAccountId());
        if (account.isPresent()) {
            ArrayList<Account> starredAccounts = (ArrayList<Account>) account.get().getMyStarredList();
            if (starredAccounts.size() > 0)
            return new ResponseEntity<>(starredAccounts, HttpStatus.OK);
            else return new ResponseEntity<>(starredAccounts, HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(new ArrayList<Account>(), HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<Boolean> deleteStarredAccount(JsonRequestBody<Account> requestBody) {
        if (accountRepo.findById(requestBody.getToken().getAccountId()).isPresent()) {
            Account account = accountRepo.findById(requestBody.getToken().getAccountId()).get();
            return new ResponseEntity<>(account.getMyStarredList().remove(requestBody.getObject()), HttpStatus.OK);
        } return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }
}
