package com.ticketsystem.models.dto;

import com.ticketsystem.models.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthDto {
    Long id;
    String accessToken;
    String name;
    String surname;
    String email;
    String role;
}
