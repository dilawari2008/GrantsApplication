package com.example.grantsmanagement.GrantsManagement.dto;

public class CreateNonProfitDto {
    private String name;
    private String address;
    private String email;
    private Long foundationId;

    public Long getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(Long foundationId) {
        this.foundationId = foundationId;
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
}
