package com.revature.networkingassistant.beans.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Locations")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "addressNum")
    private String addressNum;

    @Column(name = "streetName")
    private String streetName;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

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
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(event, location.event) &&
                Objects.equals(addressNum, location.addressNum) &&
                Objects.equals(streetName, location.streetName) &&
                Objects.equals(city, location.city) &&
                state == location.state &&
                Objects.equals(zip, location.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, addressNum, streetName, city, state, zip);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", event=" + event +
                ", addressNum='" + addressNum + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", zip='" + zip + '\'' +
                '}';
    }
}
