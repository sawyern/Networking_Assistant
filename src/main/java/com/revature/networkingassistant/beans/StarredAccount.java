package com.revature.networkingassistant.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "StarredAccounts")
public class StarredAccount {

    @Id
    @Column(name = "id")
    @JsonView(Views.Public.class)
    private int id;

    @Column(name = "ownerId")
    @JsonView(Views.Public.class)
    private int ownerId;

    @Column(name = "starredAccountId")
    @JsonView(Views.Public.class)
    private int starredAccountId;

    public StarredAccount() {
    }

    public StarredAccount(int id, int ownerId, int starredAccountId) {
        this.id = id;
        this.ownerId = ownerId;
        this.starredAccountId = starredAccountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getStarredAccountId() {
        return starredAccountId;
    }

    public void setStarredAccountId(int starredAccountId) {
        this.starredAccountId = starredAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarredAccount that = (StarredAccount) o;
        return id == that.id &&
                ownerId == that.ownerId &&
                starredAccountId == that.starredAccountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, starredAccountId);
    }

    @Override
    public String toString() {
        return "StarredAccount{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", starredAccountId=" + starredAccountId +
                '}';
    }
}
