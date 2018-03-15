package com.revature.networkingassistant.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "EventInvites")
public class Invite implements Serializable {

    @Id
    @Column(name = "eventId")
    private int eventId;

    @Id
    @Column(name = "accountId")
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
