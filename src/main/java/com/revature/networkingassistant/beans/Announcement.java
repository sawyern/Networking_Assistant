package com.revature.networkingassistant.beans;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Announcements")
@IdClass(Announcement.class)
public class Announcement implements Serializable{

    @Id
    @Column(name = "eventId", updatable = false, nullable = false)
    private int eventId;

    @Id
    @Column(name = "accountId", updatable = false, nullable = false)
    private int accountId;

    @Column(name = "message", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return eventId == that.eventId &&
                accountId == that.accountId &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, accountId, message);
    }
}
