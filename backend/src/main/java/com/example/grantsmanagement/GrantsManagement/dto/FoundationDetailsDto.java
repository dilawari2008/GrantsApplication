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
}
