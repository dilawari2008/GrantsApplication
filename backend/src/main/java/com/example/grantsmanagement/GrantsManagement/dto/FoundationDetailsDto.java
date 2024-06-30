package com.example.grantsmanagement.GrantsManagement.dto;

public class FoundationDetailsDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private Long foundationId;
    private String foundationName;
    private String foundationEmail;


    public FoundationDetailsDto(Long userId, String firstName, String lastName, String username, Long foundationId, String foundationName, String foundationEmail) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.foundationId = foundationId;
        this.foundationName = foundationName;
        this.foundationEmail = foundationEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getFoundationName() {
        return foundationName;
    }

    public void setFoundationName(String foundationName) {
        this.foundationName = foundationName;
    }

    public String getFoundationEmail() {
        return foundationEmail;
    }

    public void setFoundationEmail(String foundationEmail) {
        this.foundationEmail = foundationEmail;
    }

    @Override
    public String toString() {
        return "FoundationDetailsDto{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", foundationId=" + foundationId +
                ", foundationName='" + foundationName + '\'' +
                ", foundationEmail='" + foundationEmail + '\'' +
                '}';
    }
}
