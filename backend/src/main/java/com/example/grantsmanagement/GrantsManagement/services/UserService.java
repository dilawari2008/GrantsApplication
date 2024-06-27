package com.example.grantsmanagement.GrantsManagement.services;

import com.example.grantsmanagement.GrantsManagement.dto.CreateFoundationDto;
import com.example.grantsmanagement.GrantsManagement.models.User;
import com.example.grantsmanagement.GrantsManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser (CreateFoundationDto createFoundationDto, Long foundationId) {
        User user = new User(createFoundationDto.getFirstName(), createFoundationDto.getLastName(), createFoundationDto.getUsername(), foundationId);
        userRepository.save(user);
    }

    public ResponseEntity<Long> getFoundationIdByUsername (String username) {
        User user = userRepository.findUserByUsername(username);
        return ResponseEntity.ok(user.getFoundationId());
    }
}
