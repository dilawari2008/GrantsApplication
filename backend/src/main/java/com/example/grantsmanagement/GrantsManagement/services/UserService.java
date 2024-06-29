package com.example.grantsmanagement.GrantsManagement.services;

import com.example.grantsmanagement.GrantsManagement.dto.CreateFoundationDto;
import com.example.grantsmanagement.GrantsManagement.dto.FoundationDetailsDto;
import com.example.grantsmanagement.GrantsManagement.models.Foundation;
import com.example.grantsmanagement.GrantsManagement.models.User;
import com.example.grantsmanagement.GrantsManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoundationService foundationService;

    public void createUser (CreateFoundationDto createFoundationDto, Long foundationId) {
        User user = new User(createFoundationDto.getFirstName(), createFoundationDto.getLastName(), createFoundationDto.getUsername(), foundationId);
        userRepository.save(user);
    }

    public ResponseEntity<FoundationDetailsDto> getFoundationIdByUsername (String username) {
        User user = userRepository.findUserByUsername(username);
        Foundation foundation = foundationService.getFoundationById(user.getFoundationId());
        FoundationDetailsDto foundationDetailsDto = new FoundationDetailsDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getFoundationId(), foundation.getName(), foundation.getEmail());
        return ResponseEntity.ok(foundationDetailsDto);
    }
}
