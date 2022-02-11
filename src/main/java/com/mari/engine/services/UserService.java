package com.mari.engine.services;

import com.mari.engine.entities.User;
import com.mari.engine.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void registerUser(User user) {
        User userFromDB = userRepo.findUserByEmail(user.getEmail());
        if (userFromDB != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email " + user.getEmail() + " is already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
    public User getUserByEmail(String username) {
        return userRepo.findUserByEmail(username);
    }
}
