package com.revature.networkingassistant.beans;


import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Announcements")
public class Announcement implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue
    @JsonView(Views.Public.class)
    private int id;

    @Column(name = "eventId")
    @JsonView(Views.Public.class)
    private int eventId;

    @Column(name = "accountId")
    @JsonView(Views.Public.class)
    private int accountId;

    @Column(name = "message")
    @JsonView(Views.Public.class)
    private String message;

    public Announcement() {
    }

    public Announcement(int eventId, int accountId, String message) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.message = message;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return id == that.id &&
                eventId == that.eventId &&
                accountId == that.accountId &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, accountId, message);
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", accountId=" + accountId +
                ", message='" + message + '\'' +
                '}';
    }
}
