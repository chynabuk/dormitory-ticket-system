package com.ticketsystem.services.user.impl;

import com.ticketsystem.models.entities.User;
import com.ticketsystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUsernameFinderService {
    private final UserRepository userRepository;
    public User findByEmail(String email){
        return new User();
    }
}
