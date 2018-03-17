package com.revature.networkingassistant.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "StarredAccounts")
public class StarredAccount {

    @Id
    @Column(name = "id")
    @JsonView(Views.Public.class)
    private String id;

    @Column(name = "owner")
    @JsonView(Views.Public.class)
    private int ownerId;

    @Column(name = "starredAccount")
    @JsonView(Views.Public.class)
    private int starredAccountId;

    public StarredAccount() {
    }

    public StarredAccount(String id, int ownerId, int starredAccountId) {
        this.ownerId = ownerId;
        this.starredAccountId = starredAccountId;
        this.id = String.valueOf(ownerId) + "|" + String.valueOf(starredAccountId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
