package com.example.grantsmanagement.GrantsManagement.controllers;

import com.example.grantsmanagement.GrantsManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{username}")
    public ResponseEntity<Long> getFoundationIdByUsername(@PathVariable String username){
        return userService.getFoundationIdByUsername(username);
    }
}
