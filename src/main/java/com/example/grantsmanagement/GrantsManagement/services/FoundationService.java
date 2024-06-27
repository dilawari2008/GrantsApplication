package com.example.grantsmanagement.GrantsManagement.services;

import com.example.grantsmanagement.GrantsManagement.dto.CreateFoundationDto;
import com.example.grantsmanagement.GrantsManagement.models.Foundation;
import com.example.grantsmanagement.GrantsManagement.repositories.FoundationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class FoundationService {

    @Autowired
    private FoundationRepository foundationRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public ResponseEntity<Long> createFoundation (CreateFoundationDto createFoundationDto) {
        Foundation foundation = new Foundation(createFoundationDto.getFoundationName(), createFoundationDto.getEmail(), new Date());
        Foundation newFoundation = foundationRepository.save(foundation);
        userService.createUser(createFoundationDto, newFoundation.getId());
        return ResponseEntity.ok(newFoundation.getId());
    }

    public Foundation getFoundationById (Long foundationId) {
        Optional<Foundation> optionalFoundation = foundationRepository.findById(foundationId);
        return optionalFoundation.get();
    }
}
