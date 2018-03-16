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
    @Column(name = "id")
    private String id;

    @JsonView(Views.Public.class)
    @JoinColumn(name="Events", updatable = false, nullable = false)
    private int eventId;

    @JsonView(Views.Public.class)
    @JoinColumn(name="Accounts", updatable = false, nullable = false)
    private int inviterId;

    @JsonView(Views.Public.class)
    @JoinColumn(name="Accounts", updatable = false, nullable = false)
    private int inviteeId;

    public Invite() {
    }

    public Invite(int eventId, int inviterId, int inviteeId) {
        this.eventId = eventId;
        this.inviterId = inviterId;
        this.inviteeId = inviteeId;
        this.id = String.valueOf(eventId) + "|"  + String.valueOf(inviterId) + "|" + String.valueOf(inviteeId);
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getInviterId() {
        return inviterId;
    }

    public void setInviterId(int inviterId) {
        this.inviterId = inviterId;
    }

    public int getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(int inviteeId) {
        this.inviteeId = inviteeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invite invite = (Invite) o;
        return eventId == invite.eventId &&
                inviterId == invite.inviterId &&
                inviteeId == invite.inviteeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, inviterId, inviteeId);
    }

    @Override
    public String toString() {
        return "Invite{" +
                "eventId=" + eventId +
                ", inviterId=" + inviterId +
                ", inviteeId=" + inviteeId +
                '}';
    }
}
