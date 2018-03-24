package com.revature.networkingassistant.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Invites")
@IdClass(Invite.class)
public class Invite implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @JsonView(Views.Public.class)
    @JoinColumn(name="Events", updatable = false, nullable = false)
    private int eventId;

    @JsonView(Views.Public.class)
    @JoinColumn(name="Accounts", updatable = false, nullable = false)
    private int inviter;

    @JsonView(Views.Public.class)
    @JoinColumn(name="Accounts", updatable = false, nullable = false)
    private int invitee;

    public Invite() {
    }

    public Invite(int eventId, int inviter, int invitee) {
        this.eventId = eventId;
        this.inviter = inviter;
        this.invitee = invitee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getInviter() {
        return inviter;
    }

    public void setInviter(int inviter) {
        this.inviter = inviter;
    }

    public int getInvitee() {
        return invitee;
    }

    public void setInvitee(int invitee) {
        this.invitee = invitee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invite invite = (Invite) o;
        return id == invite.id &&
                eventId == invite.eventId &&
                inviter == invite.inviter &&
                invitee == invite.invitee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, inviter, invitee);
    }

    @Override
    public String toString() {
        return "Invite{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", inviter=" + inviter +
                ", invitee=" + invitee +
                '}';
    }
}
