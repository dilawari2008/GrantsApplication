package com.example.grantsmanagement.GrantsManagement.models;

import com.example.grantsmanagement.GrantsManagement.enums.MailStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String body;
    private String subject;
    private Long foundationId;
    private String foundationEmail;
    private String nonProfitEmail;
    private Long nonProfitId;
    private Date createdAt;
    private MailStatus mailStatus;

    public Email(String body, String subject, Long foundationId, String foundationEmail, String nonProfitEmail, Long nonProfitId, Date createdAt, MailStatus mailStatus) {
        this.body = body;
        this.subject = subject;
        this.foundationId = foundationId;
        this.foundationEmail = foundationEmail;
        this.nonProfitEmail = nonProfitEmail;
        this.nonProfitId = nonProfitId;
        this.createdAt = createdAt;
        this.mailStatus = mailStatus;
    }

    public Email() {
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(Long foundationId) {
        this.foundationId = foundationId;
    }

    public String getFoundationEmail() {
        return foundationEmail;
    }

    public void setFoundationEmail(String foundationEmail) {
        this.foundationEmail = foundationEmail;
    }

    public String getNonProfitEmail() {
        return nonProfitEmail;
    }

    public void setNonProfitEmail(String nonProfitEmail) {
        this.nonProfitEmail = nonProfitEmail;
    }

    public Long getNonProfitId() {
        return nonProfitId;
    }

    public void setNonProfitId(Long nonProfitId) {
        this.nonProfitId = nonProfitId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public MailStatus getMailStatus() {
        return mailStatus;
    }

    public void setMailStatus(MailStatus mailStatus) {
        this.mailStatus = mailStatus;
    }
}
