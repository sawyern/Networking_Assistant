package com.revature.networkingassistant.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;

@Entity
@Table(name = "Attendants")
public class Attendant {

    //Primary key for junction tables is a String: eventId|accountID
    @Id
    @Column(name = "id")
    @JsonView(Views.Public.class)
    private String id;

    @Column(name = "eventId")
    @JsonView(Views.Public.class)
    private int eventId;

    @Column(name = "accountId")
    @JsonView(Views.Public.class)
    private int accountId;

    @Column(name = "role")
    @JsonView(Views.Public.class)
    private String role;

    public Attendant() {
    }

    public Attendant(int eventId, int accountId, String role) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.role = role;
        //primary key is a combination of both foreign keys
        this.id = String.valueOf(eventId) + "|" + String.valueOf(accountId);
    }

    public String getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
