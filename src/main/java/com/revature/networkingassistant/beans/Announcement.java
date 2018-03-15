package com.revature.networkingassistant.beans;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Announcements")
@IdClass(Announcement.class)
public class Announcement implements Serializable{

    @Id
    @Column(name = "eventId")
    private int eventId;

    @Id
    @Column(name = "accountId")
    private int accountId;

    @Column(name = "message")
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
