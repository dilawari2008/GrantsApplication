package com.example.grantsmanagement.GrantsManagement.dto;

public class EditTemplateDto {
    private Long foundationId;
    private Long nonProfitId;
    private String template;

    public EditTemplateDto(Long foundationId, Long nonProfitId, String template) {
        this.foundationId = foundationId;
        this.nonProfitId = nonProfitId;
        this.template = template;
    }

    public Long getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(Long foundationId) {
        this.foundationId = foundationId;
    }

    public Long getNonProfitId() {
        return nonProfitId;
    }

    public void setNonProfitId(Long nonProfitId) {
        this.nonProfitId = nonProfitId;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
