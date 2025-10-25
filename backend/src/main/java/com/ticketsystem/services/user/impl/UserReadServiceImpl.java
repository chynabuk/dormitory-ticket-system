package com.ticketsystem.services.user.impl;

import com.ticketsystem.exceptions.impl.ResourceNotFoundException;
import com.ticketsystem.models.dto.UserDetailDto;
import com.ticketsystem.models.dto.UserDto;
import com.ticketsystem.models.entities.User;
import com.ticketsystem.models.mappers.UserMapper;
import com.ticketsystem.repositories.UserRepository;
import com.ticketsystem.services.user.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReadServiceImpl implements UserReadService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;
    @Override
    public UserDto getProfile() {
        return userMapper.apply(getUser());
    }

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        return ((UserDetailDto) userDetailsService.loadUserByUsername((String) authentication.getPrincipal())).getUser();
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper).toList();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found!");
        }
        return userOptional.get();
    }
}
