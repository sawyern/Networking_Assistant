package com.revature.networkingassistant.beans.Attendant;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Attendants")
public class Attendant implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @JsonView(Views.Public.class)
    @JoinColumn(name="Events", updatable = false, nullable = false)
    private int eventId;

    @JsonView(Views.Public.class)
    @JoinColumn(name="Accounts", updatable = false, nullable = false)
    private int accountId;

    @JsonView(Views.Public.class)
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Attendant() {
    }

    public Attendant(int eventId, int accountId, Role role) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.role = role;
        this.id = String.valueOf(eventId) + "|" + String.valueOf(accountId);
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendant attendant = (Attendant) o;
        return eventId == attendant.eventId &&
                accountId == attendant.accountId &&
                role == attendant.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, accountId, role);
    }

    @Override
    public String toString() {
        return "Attendant{" +
                "eventId=" + eventId +
                ", accountId=" + accountId +
                ", role=" + role +
                '}';
    }
}
