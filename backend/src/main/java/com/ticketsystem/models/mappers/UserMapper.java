package com.ticketsystem.models.mappers;

import com.ticketsystem.models.dtos.UserDto;
import com.ticketsystem.models.entities.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return UserDto.builder()
                .name(user.getFirstName())
                .surname(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .apartmentNumber(user.getApartmentNumber())
                .roomNumber(user.getRoomNumber())
                .build();
    }
}
