package com.ticketsystem.services.user;

import com.ticketsystem.models.dto.UserAuthDto;
import com.ticketsystem.models.dto.UserDto;
import com.ticketsystem.models.requests.user.UserAuthRequest;
import com.ticketsystem.models.requests.user.UserCreateRequest;

public interface UserWriteService {
    UserAuthDto login(UserAuthRequest request);
    UserDto createUser(UserCreateRequest request);
}
