package com.revature.networkingassistant.services.StarService;

import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.repositories.AccountRepo;
import com.revature.networkingassistant.repositories.AttendantRepo;
import com.revature.networkingassistant.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StarService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    AttendantRepo attendantRepo;

    @Transactional
    public ResponseEntity<Account> starAccount(int ownerId, int starredId) {
        Optional<Account> starredAccount = accountRepo.findById(ownerId);
        Optional<Account> ownerAccount = accountRepo.findById(starredId);
        if (ownerAccount.isPresent()) {
            if (starredAccount.isPresent()) {
                ownerAccount.get().getStarredAccounts().add(starredAccount.get());
                return new ResponseEntity<>(starredAccount.get(), HttpStatus.CREATED);
            } return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
        } return new ResponseEntity<>(new Account(), HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<ArrayList<Account>> getStarredAccounts(int id) {
        Optional<Account> account = accountRepo.findById(id);
        if (account.isPresent()) {
            ArrayList<Account> starredAccounts = (ArrayList<Account>) account.get().getStarredAccounts();
            if (starredAccounts.size() > 0)
            return new ResponseEntity<>(starredAccounts, HttpStatus.OK);
            else return new ResponseEntity<>(starredAccounts, HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(new ArrayList<Account>(), HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<ArrayList<Account>> getEventStarredAccounts(int eventId, int id) {
        Optional<Account> account = accountRepo.findById(id);
        Optional<Event> event = eventRepo.findById(id);
        ArrayList<Account> eventStarredAccounts = new ArrayList<>();
        if (account.isPresent() && event.isPresent()) {
            ArrayList<Attendant> eventAttendants = attendantRepo.findByEventId(eventId);
            ArrayList<Account> starredAccounts = (ArrayList<Account>) account.get().getStarredAccounts();
            for (Account starred : starredAccounts) {
                for (Attendant attendant: eventAttendants) {
                    if (starred.getId() == attendant.getAccountId()) {
                        eventStarredAccounts.add(starred);
                    }
                }
            }
            if (eventStarredAccounts.size() > 0)
            return new ResponseEntity<>(eventStarredAccounts, HttpStatus.OK);
            else return new ResponseEntity<>(eventStarredAccounts, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventStarredAccounts, HttpStatus.BAD_REQUEST);
    }
}
