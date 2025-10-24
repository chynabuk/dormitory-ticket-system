package com.ticketsystem.services.user.impl;

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
        return ((UserDetailDto) userDetailsService.loadUserByUsername((String) authentication.getPrincipal())).getUser();
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper).toList();
    }
}
