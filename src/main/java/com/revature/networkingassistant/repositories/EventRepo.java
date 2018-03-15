package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends CrudRepository<Event, Integer> {
}
