package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Attendant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendantRepo extends CrudRepository<Attendant, String> {

    Iterable<Attendant> findByIdStartingWith(String eventId);

    Iterable<Attendant> findByIdEndingWith(String accountId);
}
