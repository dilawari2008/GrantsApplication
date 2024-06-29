package com.example.grantsmanagement.GrantsManagement.dto;

public class TemplateVariablesDto {
    private String name;
    private String address;
    private String email;
    private String foundation_name;

    public TemplateVariablesDto(String name, String address, String email, String foundation_name) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.foundation_name = foundation_name;
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

    public String getFoundation_name() {
        return foundation_name;
    }

    public void setFoundation_name(String foundation_name) {
        this.foundation_name = foundation_name;
    }
}
