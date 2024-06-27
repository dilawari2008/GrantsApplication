package com.example.grantsmanagement.GrantsManagement.dto;

import java.util.List;

public class EmailDto {
    private Long foundationId;
    private List<Long> nonprofitIds;
    private String body;

    public Long getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(Long foundationId) {
        this.foundationId = foundationId;
    }

    public List<Long> getNonprofitIds() {
        return nonprofitIds;
    }

    public void setNonprofitIds(List<Long> nonprofitIds) {
        this.nonprofitIds = nonprofitIds;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
