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

    public ResponseEntity<Account> getById(int id) {
        try {
            Account result = accountRepo.findById(id).get();
            if (result == null) {
                return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
            } else return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<Account> getByEmail(String email) {
        try {
            Account result = accountRepo.findByEmail(email);
            if (result == null) {
                return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
            } else return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<Account> getByFirstName(String name) {
        try {
            Account result = accountRepo.findByFirstName(name);
            if (result == null) {
                return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
            } else return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<Account> getByLastName(String name) {
        try {
            Account result = accountRepo.findByLastName(name);
            if (result == null) {
                return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
            } else return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Account(), HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<ArrayList<Account>> getByPartialEmail(String partialEmail) {
        try {
            return new ResponseEntity<>(accountRepo.findAccountsByEmailContaining(partialEmail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Account>(), HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<ArrayList<Account>> getByPartialFirstName(String partialEmail) {
        try {
            return new ResponseEntity<>(accountRepo.findAccountsByFirstNameContaining(partialEmail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Account>(), HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<ArrayList<Account>> getByPartialLastName(String partialEmail) {
        try {
            return new ResponseEntity<>(accountRepo.findAccountsByLastNameContaining(partialEmail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<Account>(), HttpStatus.BAD_GATEWAY);
        }
    }
}
