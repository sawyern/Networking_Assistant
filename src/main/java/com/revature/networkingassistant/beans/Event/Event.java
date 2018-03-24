package com.revature.networkingassistant.beans.Event;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.beans.Account;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @JsonView(Views.Public.class)
    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "event", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Location location;

    @JsonView(Views.Public.class)
    @Column(name = "date")
    private Date date;

    @JsonView(Views.Public.class)
    @Column(name = "description")
    private String description;

    public Event() {
    }

    public Event(String name, Location location, Date date, String description) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return id == event.id &&
                Objects.equals(name, event.name) &&
                Objects.equals(date, event.date) &&
                Objects.equals(description, event.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, description);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", description=" + description +
                '}';
    }
}
