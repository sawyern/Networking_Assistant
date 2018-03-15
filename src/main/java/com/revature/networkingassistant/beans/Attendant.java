package com.revature.networkingassistant.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Attendants")
public class Attendant {

    private int eventId;
    private int accountId;
    private String role;

    public Attendant() {
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
