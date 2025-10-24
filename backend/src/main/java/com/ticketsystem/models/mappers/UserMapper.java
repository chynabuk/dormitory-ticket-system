package com.ticketsystem.models.mappers;

import com.ticketsystem.models.dtos.UserDto;
import com.ticketsystem.models.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
