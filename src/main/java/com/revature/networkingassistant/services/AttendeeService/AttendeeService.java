package com.revature.networkingassistant.services.AttendeeService;

import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.repositories.AttendantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AttendeeService {

    private AttendantRepo attendantRepo;

    public AttendeeService() {}

    @Autowired
    public AttendeeService(AttendantRepo attendantRepo) {
        this.attendantRepo = attendantRepo;
    }

    public ResponseEntity<ArrayList<Attendant>> getAttendees(int eventId) {
        ArrayList<Attendant> attendants = attendantRepo.findByEventId(eventId);
        if (attendants != null) {
            if (attendants.size() > 0)
                return new ResponseEntity<>(attendants, HttpStatus.OK);
            else return new ResponseEntity<>(attendants, HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
    }
}
