package com.revature.networkingassistant.services.AttendantService;

import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.beans.Event.Event;
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

    @Autowired
    AttendantRepo attendantRepo;

    @Autowired
    EventRepo eventRepo;

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
}
