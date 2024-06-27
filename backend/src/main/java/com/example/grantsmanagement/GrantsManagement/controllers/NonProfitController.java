package com.example.grantsmanagement.GrantsManagement.controllers;

import com.example.grantsmanagement.GrantsManagement.dto.CreateNonProfitDto;
import com.example.grantsmanagement.GrantsManagement.models.NonProfit;
import com.example.grantsmanagement.GrantsManagement.services.NonProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app")
public class NonProfitController {

    @Autowired
    private NonProfitService nonProfitService;

    @PostMapping("/non-profits")
    public ResponseEntity<List<NonProfit>> createNonProfit (@RequestBody CreateNonProfitDto createNonProfitDto){
        return nonProfitService.createNonProfit(createNonProfitDto);
    }

    @GetMapping("/non-profits/{foundationId}")
    public ResponseEntity<List<NonProfit>> getAllNonProfits (@PathVariable  Long foundationId) {
        return nonProfitService.getAllNonProfits(foundationId);
    }
}
