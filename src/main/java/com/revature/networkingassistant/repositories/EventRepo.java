package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Event.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EventRepo extends CrudRepository<Event, Integer> {
    void deleteByName(String name);
    Event findByName(String name);
    boolean existsByName(String name);

    @Override
    ArrayList<Event> findAll();
    ArrayList<Event> findEventsByName (String name);
    ArrayList<Event> findEventsByLocation (String location);
    ArrayList<Event> findEventsByDate (String date);
}