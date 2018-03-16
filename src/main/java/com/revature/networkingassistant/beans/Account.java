package com.revature.networkingassistant.beans;

import com.fasterxml.jackson.annotation.JsonView;
import com.revature.networkingassistant.controllers.DTO.Views;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    @JsonView(Views.Public.class)
    private int id;

    @JsonView(Views.Public.class)
    @Column(name = "email")
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @JsonView(Views.Public.class)
    @Column(name = "firstName")
    private String firstName;

    @JsonView(Views.Public.class)
    @Column(name = "lastName")
    private String lastName;

    @JsonView(Views.Public.class)
    @Column(name = "phone")
    private String phone;

    @JsonView(Views.Public.class)
    @Column(name = "background")
    private String background;

    @JsonView(Views.Public.class)
    @Column(name = "zipCode")
    private String zipCode;

    @JsonView(Views.Public.class)
    @Column(name = "attachment")
    @Lob
    private byte[] attachment;

    public Account() {
    }

    public Account(String email, String passwordHash, String phone, String background, String zipCode, byte[] attachment) {
    public Account(String email, String passwordHash, String firstName, String lastName, String phone, String background, String zipCode, Byte[] attachment) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.background = background;
        this.zipCode = zipCode;
        this.attachment = attachment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(email, account.email) &&
                Objects.equals(passwordHash, account.passwordHash) &&
                Objects.equals(firstName, account.firstName) &&
                Objects.equals(lastName, account.lastName) &&
                Objects.equals(phone, account.phone) &&
                Objects.equals(background, account.background) &&
                Objects.equals(zipCode, account.zipCode) &&
                Arrays.equals(attachment, account.attachment);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, email, passwordHash, phone, background, zipCode);
        result = 31 * result + Arrays.hashCode(attachment);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", background='" + background + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", attachment=" + Arrays.toString(attachment) +
                '}';
    }
}
