package com.revature.networkingassistant.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Invite implements Serializable {

    //Primary key for junction tables is a String: eventId|accountID
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "eventId")
    private int eventId;

    @Column(name = "accountId")
    private int accountId;

    public Invite() {
    }

    public Invite(int eventId, int accountId) {
        this.eventId = eventId;
        this.accountId = accountId;
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
}
