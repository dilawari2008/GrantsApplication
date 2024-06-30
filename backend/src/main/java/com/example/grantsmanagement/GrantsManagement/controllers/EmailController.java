package com.example.grantsmanagement.GrantsManagement.controllers;

import com.example.grantsmanagement.GrantsManagement.dto.EmailDto;
import com.example.grantsmanagement.GrantsManagement.models.Email;
import com.example.grantsmanagement.GrantsManagement.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/listing/{foundationId}")
    public ResponseEntity<Page> getAllEmails (@PathVariable Long foundationId, @RequestParam(defaultValue = "1") int pageNumber,
                                                     @RequestParam(defaultValue = "5") int pageSize) {
        return emailService.getAllEmails(foundationId, pageNumber, pageSize);
    }

    @PostMapping("/")
    public ResponseEntity sendMoneyToMultipleNonProfits (@RequestBody EmailDto emailDto) throws Exception {
        emailService.sendMoneyToMultipleNonProfits(emailDto.getNonprofitIds(), emailDto.getFoundationId());
        return ResponseEntity.ok("Initated emails sending, view dashboard for details.");
    }
}
