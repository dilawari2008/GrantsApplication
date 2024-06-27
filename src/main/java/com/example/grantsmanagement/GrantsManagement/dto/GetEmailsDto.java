package com.example.grantsmanagement.GrantsManagement.dto;

import java.util.List;

public class GetEmailsDto {
    private Long foundationId;
    private List<String> nonProfitEmails;

    public Long getFoundationId() {
        return foundationId;
    }

    public List<String> getNonProfitEmails() {
        return nonProfitEmails;
    }
}
