package com.example.grantsmanagement.GrantsManagement.controllers;

import com.example.grantsmanagement.GrantsManagement.dto.EmailDto;
import com.example.grantsmanagement.GrantsManagement.dto.GetEmailsDto;
import com.example.grantsmanagement.GrantsManagement.models.Email;
import com.example.grantsmanagement.GrantsManagement.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/emails/listing")
    public ResponseEntity<List<Email>> getAllEmailsForSelectedNonProfits (@RequestBody GetEmailsDto getEmailsDto) {
        return emailService.getAllEmailsForSelectedNonProfits(getEmailsDto);
    }

    @PostMapping("/emails")
    public ResponseEntity sendMoneyToMultipleNonProfits (@RequestBody EmailDto emailDto) {
        emailService.sendMoneyToMultipleNonProfits(emailDto.getNonprofitIds(), emailDto.getFoundationId());
        return ResponseEntity.ok("Initated emails sending, view dashboard for details.");
    }
}
