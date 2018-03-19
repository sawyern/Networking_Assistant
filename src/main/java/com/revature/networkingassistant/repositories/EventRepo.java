package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Integer> {
    void deleteByName(String name);
    Event findByName(String name);
    boolean existsByName(String name);
}
