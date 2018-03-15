package com.revature.networkingassistant.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EventInvites")
public class Invite {

    private int eventId;
    private int accountId;

    public Invite() {
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
