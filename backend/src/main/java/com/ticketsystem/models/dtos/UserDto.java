package com.ticketsystem.models.dtos;

import com.ticketsystem.models.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String name;
    String surname;
    String email;
    String role;
    String apartmentNumber;
    String roomNumber;
}
