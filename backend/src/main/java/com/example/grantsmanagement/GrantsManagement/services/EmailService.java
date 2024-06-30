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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class EmailService {

    private final Integer MAX_BATCH_SIZE = 10;
    private final Integer MAILING_TIME = 3000;

    private static Logger log = LoggerFactory.getLogger(FoundationService.class);

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private NonProfitService nonProfitService;

    @Autowired
    private FoundationService foundationService;

    public ResponseEntity<Page> getAllEmails (Long foundationId, int pageNumber, int pageSize, Long nonProfitId) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("createdAt").descending());
        Page emails = null;
        if (nonProfitId != null) {
            emails = emailRepository.findAllEmailsByFoundationIdNonProfitId(foundationId, nonProfitId, pageable);
        } else {
            emails = emailRepository.findAllEmailsByFoundationId(foundationId, pageable);
        }
        log.info(emails.toString());
        return ResponseEntity.ok(emails);
    }

    public void sendMailToMultipleNonProfits(List<Long> nonProfitIds, Long foundationId, String customTemplate) throws Exception {

        if (nonProfitIds != null && nonProfitIds.size() > MAX_BATCH_SIZE) {
            throw new Exception(String.format("Max Batch Size is %s", MAX_BATCH_SIZE));
        }

        List<NonProfit> nonProfits = nonProfitService.getNonProfitsByNonProfitIds(nonProfitIds);
        Foundation foundation = foundationService.getFoundationById(foundationId);

        List<Email> emails = new ArrayList<>();
        for (NonProfit nonProfit : nonProfits) {
            String template = customTemplate != null ? customTemplate : nonProfit.getTemplate();
            TemplateVariablesDto templateVariablesDto = new TemplateVariablesDto(nonProfit.getName(),nonProfit.getAddress(),nonProfit.getEmail(), foundation.getName());
            Email email = new Email();
            email.setBody(String.format(StringUtils.populateTemplates(template, templateVariablesDto)));
            email.setSubject(String.format("Sending Money to %s", nonProfit.getName()));
            email.setFoundationId(foundationId);
            email.setFoundationEmail(foundation.getEmail());
            email.setNonProfitEmail(nonProfit.getEmail());
            email.setNonProfitId(nonProfit.getId());
            email.setCreatedAt(new Date());
            email.setMailStatus(MailStatus.QUEUED);

            emails.add(email);
        }


        List<Email> savedEmails = emailRepository.saveAll(emails);


        log.info("Tasks queued: " + savedEmails.size());

        CompletableFuture<Void> processingResult = processEmails(savedEmails);

        processingResult.thenRun(() -> {
            log.info("MAIL PROCESSING DONE!");
        }).exceptionally(e -> {
            log.error("Error while processing emails");
            return null;
        });
    }

    private CompletableFuture<Void> processEmails(List<Email> savedEmails) {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_BATCH_SIZE);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Email savedEmail : savedEmails) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    // Timeout for mocking API call to mailing service
                    Thread.sleep(MAILING_TIME);
                    savedEmail.setMailStatus(MailStatus.SUCCESS);
                    savedEmail.setCreatedAt(new Date()); // using createdAt as lastUpdatedAt
                    emailRepository.save(savedEmail);
                    log.info("SUBJECT: " + savedEmail.getSubject()+"  BODY: "+ savedEmail.getBody());
                } catch (Exception e) {
                    log.error("Error while mailing", e);
                    savedEmail.setMailStatus(MailStatus.FAILED);
                    savedEmail.setCreatedAt(new Date());
                    emailRepository.save(savedEmail);
                }
            }, executor);

            futures.add(future);
        }


        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(executor::shutdown);
    }
}
