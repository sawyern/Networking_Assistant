package com.revature.networkingassistant.beans.Attendant;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Attendants")
@IdClass(Attendant.class)
public class Attendant implements Serializable {

    @Id
    @JsonView(Views.Public.class)
    @JoinColumn(name="Events")
    private int eventId;

    @Id
    @JsonView(Views.Public.class)
    @JoinColumn(name="Accounts")
    private int accountId;

    @JsonView(Views.Public.class)
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Attendant() {
    }
}
