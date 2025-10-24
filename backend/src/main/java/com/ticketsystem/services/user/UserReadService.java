package com.ticketsystem.services.user;

import com.ticketsystem.models.dto.UserDto;
import com.ticketsystem.models.entities.User;

import java.util.List;

public interface UserReadService {
    UserDto getProfile();
    User getUser();
    List<UserDto> getAll();
}
