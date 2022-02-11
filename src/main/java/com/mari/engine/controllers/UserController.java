package com.mari.engine.controllers;

import com.mari.engine.entities.User;
import com.mari.engine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody User user) {
        userService.registerUser(user);
    }
}
