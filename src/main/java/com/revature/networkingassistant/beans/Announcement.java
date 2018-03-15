package com.revature.networkingassistant.beans;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Announcements")
public class Announcement {

    private int eventId;
    private int accountId;
    private String message;

    public Announcement() {
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
