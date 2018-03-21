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

    @Autowired
    AttendantRepo attendantRepo;

    public ResponseEntity<ArrayList<Attendant>> getAttendees(int eventId) {
        ArrayList<Attendant> attendants = (ArrayList<Attendant>) attendantRepo.findByEventId(eventId);
        if (attendants.size() > 0) return new ResponseEntity<>(attendants, HttpStatus.OK);
        else return new ResponseEntity<>(attendants, HttpStatus.NO_CONTENT);
    }

}
