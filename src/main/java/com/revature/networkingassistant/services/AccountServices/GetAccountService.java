package com.revature.networkingassistant.services.AccountServices;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GetAccountService {
    private AccountRepo accountRepo;

    public GetAccountService() { }

    @Autowired
    public GetAccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public ResponseEntity<Account> getByEmail(String email) {
        try {
            return new ResponseEntity<>(accountRepo.findByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Account> getByFirstName(String name) {
        try {
            return new ResponseEntity<>(accountRepo.findByFirstName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Account> getByLastName(String name) {
        try {
            return new ResponseEntity<>(accountRepo.findByLastName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ArrayList<Account>> getByPartialEmail(String partialEmail) {
        try {
            return new ResponseEntity<>(accountRepo.findAccountsByEmailContaining(partialEmail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Account>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ArrayList<Account>> getByPartialFirstName(String partialEmail) {
        try {
            return new ResponseEntity<>(accountRepo.findAccountsByFirstNameContaining(partialEmail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Account>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ArrayList<Account>> getByPartialLastName(String partialEmail) {
        try {
            return new ResponseEntity<>(accountRepo.findAccountsByLastNameContaining(partialEmail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Account>(), HttpStatus.BAD_REQUEST);
        }
    }
}
