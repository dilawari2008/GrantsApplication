package com.example.grantsmanagement.GrantsManagement.services;

import com.example.grantsmanagement.GrantsManagement.dto.GetEmailsDto;
import com.example.grantsmanagement.GrantsManagement.models.Email;
import com.example.grantsmanagement.GrantsManagement.models.Foundation;
import com.example.grantsmanagement.GrantsManagement.models.NonProfit;
import com.example.grantsmanagement.GrantsManagement.repositories.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmailService {

    private static Logger log = LoggerFactory.getLogger(FoundationService.class);

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private NonProfitService nonProfitService;

    @Autowired
    private FoundationService foundationService;

    public ResponseEntity<List<Email>> getAllEmailsForSelectedNonProfits (GetEmailsDto getEmailsDto) {
        List<Email> emails = emailRepository.findAllEmailsByFoundationIdNonProfitIds(getEmailsDto.getFoundationId(), getEmailsDto.getNonProfitEmails());
        return ResponseEntity.ok(emails);
    }

    public void sendMoneyToMultipleNonProfits (List<Long> nonProfitIds, Long foundationId) {
        List<NonProfit> nonProfits = nonProfitService.getNonProfitsByNonProfitIds(nonProfitIds);
        Foundation foundation = foundationService.getFoundationById(foundationId);
        bulkEmailHandler(nonProfits, foundation);
    }

    private void bulkEmailHandler(List<NonProfit> nonProfits, Foundation foundation) {
        log.info("Initiating funds transfer for {} foundation...", foundation.getName());
        for (NonProfit nonProfit: nonProfits) {
            String subject = String.format("Subject: Sending Money to %s", nonProfit.getName());
            String body = String.format("Body: Sending money to nonprofit %s at address %s", nonProfit.getName(), nonProfit.getAddress());
            log.info("Sending Email...");
            log.info(subject + "\n" + body);
            Email email = new Email(body, subject, foundation.getId(), foundation.getEmail(), nonProfit.getEmail(), nonProfit.getId(), new Date());
            emailRepository.save(email);
        }
    }
}