package com.revature.networkingassistant.services.AttendantService;

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

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AttendantService {

    private AttendantRepo attendantRepo;
    private EventRepo eventRepo;
    private AccountRepo accountRepo;

    public AttendantService() {}

    @Autowired
    public AttendantService(AttendantRepo attendantRepo, AccountRepo accountRepo, EventRepo eventRepo) {
        this.attendantRepo = attendantRepo;
        this.accountRepo = accountRepo;
        this.eventRepo = eventRepo;
    }

    public ResponseEntity<ArrayList<Attendant>> getAttendees(int eventId) {
        ArrayList<Attendant> attendants = (ArrayList<Attendant>) attendantRepo.findByEventId(eventId);
        if (attendants.size() > 0) return new ResponseEntity<>(attendants, HttpStatus.OK);
        else return new ResponseEntity<>(attendants, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<ArrayList<Event>> getEvents(int accountId) {
        ArrayList<Attendant> attendantEvents = attendantRepo.findByAccountId(accountId);
        ArrayList<Event> events = new ArrayList<>();
        if (attendantEvents.size() > 0) {
            for (Attendant attendant: attendantEvents) {
                Optional<Event> event = eventRepo.findById(attendant.getEventId());
                if (event.isPresent()) events.add(event.get());
            }
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        else return new ResponseEntity<>(events, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<ArrayList<Account>> getEventStarred(int eventId, int accountId) {
        ArrayList<Attendant> attendees = attendantRepo.findByEventId(eventId);
        Optional<Account> account = accountRepo.findById(accountId);
        ArrayList<Account> eventStarred = new ArrayList<>();
        if (account.isPresent()) {
            ArrayList<Account> starredAccounts = (ArrayList<Account>) account.get().getMyStarredList();
            for (Attendant attendant: attendees) {
                for (Account starred: starredAccounts) {
                    if (starred.getId() == attendant.getAccountId()) {
                        eventStarred.add(starred);
                    }
                }
            }
            return new ResponseEntity<>(eventStarred, HttpStatus.OK);
        }
        return new ResponseEntity<>(eventStarred, HttpStatus.BAD_REQUEST);
    }
}
