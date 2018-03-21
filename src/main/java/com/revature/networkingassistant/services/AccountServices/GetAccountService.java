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

    @Autowired
    private AccountRepo accountRepo;

    public ResponseEntity<Account> getByEmail(String email) {
        Account result = accountRepo.findByEmail(email);
        if (result == null) {
            return new ResponseEntity<>((Account)null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<Account> getByFirstName(String name) {
        Account result = accountRepo.findByFirstName(name);
        if (result == null) {
            return new ResponseEntity<>((Account)null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<Account> getByLastName(String name) {
        Account result = accountRepo.findByLastName(name);
        if (result == null) {
            return new ResponseEntity<>((Account)null, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<ArrayList<Account>> getByPartialEmail(String partialEmail) {
        ArrayList<Account> accounts = accountRepo.findAccountsByEmailContaining(partialEmail);
        if (accounts.size() == 0) {
            return new ResponseEntity<>(accounts, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    public ResponseEntity<ArrayList<Account>> getByPartialFirstName(String partialFirstName) {
        ArrayList<Account> accounts = accountRepo.findAccountsByFirstNameContaining(partialFirstName);
        if (accounts.size() == 0) {
            return new ResponseEntity<>(accounts, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    public ResponseEntity<ArrayList<Account>> getByPartialLastName(String partialLastName) {
        ArrayList<Account> accounts = accountRepo.findAccountsByLastNameContaining(partialLastName);
        if (accounts.size() == 0) {
            return new ResponseEntity<>(accounts, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
