package com.mari.engine.security;

import com.mari.engine.entities.User;
import com.mari.engine.repos.UserRepo;
import com.mari.engine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User " + username + " not found!");
        return new UserDetailsImpl(username, user.getPassword());
    }
}
