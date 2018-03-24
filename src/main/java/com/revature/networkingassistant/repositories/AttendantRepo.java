package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Attendant.Attendant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AttendantRepo extends CrudRepository<Attendant, String> {
    ArrayList<Attendant> findByEventId(int eventId);
    ArrayList<Attendant> findByAccountId(int accountId);
    Attendant findByAccountIdAndEventId(int accountId, int eventId);
}
