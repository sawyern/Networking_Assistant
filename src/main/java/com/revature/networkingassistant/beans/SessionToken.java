package com.revature.networkingassistant.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SessionTokens")
public class SessionToken {
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid")
    @GeneratedValue(generator = "uuid-gen")
    @JsonView(Views.Public.class)
    @Column(name = "id")
    private String id;

    @JoinColumn(name="accounts")
    @JsonView(Views.Public.class)
    private int accountId;

    public SessionToken() {}

    public SessionToken(int accountId) {
        this.accountId = accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionToken that = (SessionToken) o;
        return accountId == that.accountId &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId);
    }

    @Override
    public String toString() {
        return "SessionToken{" +
                "id='" + id + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
