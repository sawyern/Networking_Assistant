package com.revature.networkingassistant.beans;


import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Announcements")
public class Announcement implements Serializable{

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

    @Column(name = "message")
    @JsonView(Views.Public.class)
    private String message;

    public Announcement() {
    }

    public Announcement(int eventId, int accountId, String message) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
