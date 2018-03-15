package com.revature.networkingassistant.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Attendants")
@IdClass(Attendant.class)
public class Attendant implements Serializable{

    @Id
    @Column(name = "eventId")
    private int eventId;

    @Id
    @Column(name="accountId")
    private int accountId;

    @Column(name = "role")
    private String role;

    public Attendant() {
    }

    public Attendant(int eventId, int accountId, String role) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.role = role;
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
