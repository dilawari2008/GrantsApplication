package com.example.grantsmanagement.GrantsManagement.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Foundation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Date createdAt;

    public Foundation(String name, String email, Date createdAt) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Foundation() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
