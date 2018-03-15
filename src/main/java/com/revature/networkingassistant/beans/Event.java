package com.revature.networkingassistant.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    //postgresql serial generation
    @SequenceGenerator(name = "events_id_seq", sequenceName = "events_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_id_seq")
    @Column(name = "id")
    @JsonView(Views.Public.class)
    private int id;

    @Column(name = "coordinatorId")
    @JsonView(Views.Public.class)
    private int coordinatorId;

    @Column(name = "name")
    @JsonView(Views.Public.class)
    private String name;

    @Column(name = "location")
    @JsonView(Views.Public.class)
    private String location;

    @Column(name = "date")
    @JsonView(Views.Public.class)
    private String date;

    @Column(name = "time")
    @JsonView(Views.Public.class)
    private String time;

    public Event() {
    }

    public Event(int coordinatorId, String name, String location, String date, String time) {
        this.coordinatorId = coordinatorId;
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
