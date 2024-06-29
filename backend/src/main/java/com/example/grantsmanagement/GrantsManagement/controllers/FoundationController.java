package com.example.grantsmanagement.GrantsManagement.controllers;

import com.example.grantsmanagement.GrantsManagement.dto.CreateFoundationDto;
import com.example.grantsmanagement.GrantsManagement.services.FoundationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/foundations")
public class FoundationController {
    @Autowired
    private FoundationService foundationService;

    @PostMapping("/")
    public ResponseEntity<Long> createFoundation(@RequestBody CreateFoundationDto createFoundationDto){
        return foundationService.createFoundation(createFoundationDto);
    }
}
