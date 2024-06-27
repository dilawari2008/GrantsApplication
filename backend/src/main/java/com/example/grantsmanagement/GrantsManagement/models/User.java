package com.example.grantsmanagement.GrantsManagement.models;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Long foundationId;

    public User(String firstName, String lastName, String username, Long foundationId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.foundationId = foundationId;
    }

    public User() {
    }

    public Long getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(Long foundationId) {
        this.foundationId = foundationId;
    }
}
