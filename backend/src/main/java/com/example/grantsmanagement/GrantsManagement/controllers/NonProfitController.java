package com.example.grantsmanagement.GrantsManagement.controllers;

import com.example.grantsmanagement.GrantsManagement.dto.CreateNonProfitDto;
import com.example.grantsmanagement.GrantsManagement.dto.EditTemplateDto;
import com.example.grantsmanagement.GrantsManagement.models.NonProfit;
import com.example.grantsmanagement.GrantsManagement.services.NonProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app/non-profits")
public class NonProfitController {

    @Autowired
    private NonProfitService nonProfitService;

    @PostMapping("/")
    public ResponseEntity<List<NonProfit>> createNonProfit (@RequestBody CreateNonProfitDto createNonProfitDto){
        return nonProfitService.createNonProfit(createNonProfitDto);
    }

    @PutMapping("/template")
    public ResponseEntity<List<NonProfit>> updateTemplate (@RequestBody EditTemplateDto editTemplateDto){
        return nonProfitService.updateTemplate(editTemplateDto);
    }

    @GetMapping("/{foundationId}")
    public ResponseEntity<List<NonProfit>> getAllNonProfits (@PathVariable  Long foundationId) {
        return nonProfitService.getAllNonProfits(foundationId);
    }
}
