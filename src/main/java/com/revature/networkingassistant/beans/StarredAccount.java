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
}
