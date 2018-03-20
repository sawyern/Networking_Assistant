package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Attendant.Attendant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendantRepo extends CrudRepository<Attendant, String> {
    Iterable<Attendant> findByEventId(int eventId);
    Iterable<Attendant> findByAccountId(int accountId);
    Attendant findByAccountIdAndEventId(int accountId, int eventId);
}
