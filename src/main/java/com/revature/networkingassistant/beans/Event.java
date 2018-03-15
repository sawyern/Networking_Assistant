package com.revature.networkingassistant.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.beans.Attendant.Attendant;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    //postgresql serial generation
    @SequenceGenerator(name = "events_id_seq", sequenceName = "events_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_id_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @JsonView(Views.Public.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.Public.class)
    @Column(name = "location")
    private String location;

    @JsonView(Views.Public.class)
    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private String time;

    public Event() {
    }

    public Event(String name, String location, Date date, String time) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                Objects.equals(name, event.name) &&
                Objects.equals(location, event.location) &&
                Objects.equals(date, event.date) &&
                Objects.equals(time, event.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, date, time);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }
}
