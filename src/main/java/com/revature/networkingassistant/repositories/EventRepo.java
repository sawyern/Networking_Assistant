package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface EventRepo extends CrudRepository<Event, Integer> {
}
