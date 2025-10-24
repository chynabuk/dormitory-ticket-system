package com.ticketsystem.services.user;

import com.ticketsystem.models.dtos.UserAuthDto;
import com.ticketsystem.models.dtos.UserDto;
import com.ticketsystem.models.entities.User;
import com.ticketsystem.models.requests.user.UserAuthRequest;
import com.ticketsystem.models.requests.user.UserCreateRequest;

public interface UserWriteService {
    UserAuthDto login(UserAuthRequest request);
    UserDto createUser(UserCreateRequest request);
}
