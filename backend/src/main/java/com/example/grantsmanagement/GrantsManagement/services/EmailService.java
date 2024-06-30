package com.example.grantsmanagement.GrantsManagement.services;

import com.example.grantsmanagement.GrantsManagement.dto.TemplateVariablesDto;
import com.example.grantsmanagement.GrantsManagement.enums.MailStatus;
import com.example.grantsmanagement.GrantsManagement.models.Email;
import com.example.grantsmanagement.GrantsManagement.models.Foundation;
import com.example.grantsmanagement.GrantsManagement.models.NonProfit;
import com.example.grantsmanagement.GrantsManagement.repositories.EmailRepository;
import com.example.grantsmanagement.GrantsManagement.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmailService {

    private Integer MAX_BATCH_SIZE = 10;

    private static Logger log = LoggerFactory.getLogger(FoundationService.class);

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private NonProfitService nonProfitService;

    @Autowired
    private FoundationService foundationService;

    public ResponseEntity<Page> getAllEmails (Long foundationId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("createdAt").descending());
        Page emails = emailRepository.findAllEmailsByFoundationIdNonProfitIds(foundationId, pageable);
        log.info(emails.toString());
        return ResponseEntity.ok(emails);
    }

    public void sendMoneyToMultipleNonProfits (List<Long> nonProfitIds, Long foundationId) throws Exception {
        if (nonProfitIds != null && nonProfitIds.size() > MAX_BATCH_SIZE) {
            throw new Exception(String.format("Max Batch Size is %s", MAX_BATCH_SIZE));
        }
        List<NonProfit> nonProfits = nonProfitService.getNonProfitsByNonProfitIds(nonProfitIds);
        Foundation foundation = foundationService.getFoundationById(foundationId);
        bulkEmailHandler(nonProfits, foundation);
    }

    private void bulkEmailHandler(List<NonProfit> nonProfits, Foundation foundation) {
        log.info("Initiating funds transfer for {} foundation...", foundation.getName());
        for (NonProfit nonProfit: nonProfits) {
            TemplateVariablesDto templateVariablesDto = new TemplateVariablesDto(nonProfit.getName(),nonProfit.getAddress(),nonProfit.getEmail(), foundation.getName());
            String subject = String.format("Sending Money to %s", nonProfit.getName());
            String body = String.format(StringUtils.populateTemplates(nonProfit.getTemplate(), templateVariablesDto));
            log.info("Sending Email...");
            log.info(subject + "\n" + body);
            Email email = new Email(body, subject, foundation.getId(), foundation.getEmail(), nonProfit.getEmail(), nonProfit.getId(), new Date(), MailStatus.SUCCESS);
            emailRepository.save(email);
        }
    }
}
