package com.ticketsystem.services.user.impl;

import com.ticketsystem.exceptions.impl.ResourceNotFoundException;
import com.ticketsystem.models.dto.UserDetailDto;
import com.ticketsystem.models.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserUsernameFinderService usernameFinderService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usernameFinderService.findByEmail(email);
        if (user == null){
            throw new ResourceNotFoundException("User " + email + " is not found");
        }
        return new UserDetailDto(user);
    }
}
