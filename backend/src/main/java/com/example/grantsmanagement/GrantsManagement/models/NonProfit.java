package com.example.grantsmanagement.GrantsManagement.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class NonProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String email;
    private Long foundationId;
    private Date createdAt;

    public NonProfit(String name, String address, String email, Long foundationId, Date createdAt) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.foundationId = foundationId;
        this.createdAt = createdAt;
    }

    public NonProfit() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(Long foundationId) {
        this.foundationId = foundationId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
