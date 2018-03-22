package com.revature.networkingassistant.repositories;

import com.revature.networkingassistant.beans.Event.Event;
import com.revature.networkingassistant.beans.Event.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface EventRepo extends CrudRepository<Event, Integer> {
    void deleteByName(String name);
    Event findByName(String name);
    boolean existsByName(String name);

    @Override
    ArrayList<Event> findAll();
    ArrayList<Event> findEventsByName (String name);
    ArrayList<Event> findEventsByLocation (Location location);
    ArrayList<Event> findEventsByDate (Date date);
}