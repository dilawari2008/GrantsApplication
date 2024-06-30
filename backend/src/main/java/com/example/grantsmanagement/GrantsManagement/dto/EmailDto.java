package com.example.grantsmanagement.GrantsManagement.dto;

import java.util.List;

public class EmailDto {
    private Long foundationId;
    private List<Long> nonprofitIds;
    private String customTemplate;

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

    public String getCustomTemplate() {
        return customTemplate;
    }

    public void setCustomTemplate(String customTemplate) {
        this.customTemplate = customTemplate;
    }
}
