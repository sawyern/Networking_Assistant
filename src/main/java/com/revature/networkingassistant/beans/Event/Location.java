package com.revature.networkingassistant.beans.Event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne
    @JoinColumn(name = "event_id")
    @JsonManagedReference
    private Event event;

    @JsonView(Views.Public.class)
    @Column(name = "addressNum")
    private String addressNum;

    @JsonView(Views.Public.class)
    @Column(name = "streetName")
    private String streetName;

    @JsonView(Views.Public.class)
    @Column(name = "city")
    private String city;

    @JsonView(Views.Public.class)
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @JsonView(Views.Public.class)
    @Column(name = "zip")
    private String zip;

    public Location() {}

    public Location(String addressNum, String streetName, String city, State state, String zip) {
        this.addressNum = addressNum;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getAddressNum() {
        return addressNum;
    }

    public void setAddressNum(String addressNum) {
        this.addressNum = addressNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(addressNum, location.addressNum) &&
                Objects.equals(streetName, location.streetName) &&
                Objects.equals(city, location.city) &&
                state == location.state &&
                Objects.equals(zip, location.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressNum, streetName, city, state, zip);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", addressNum='" + addressNum + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", zip='" + zip + '\'' +
                '}';
    }
}
