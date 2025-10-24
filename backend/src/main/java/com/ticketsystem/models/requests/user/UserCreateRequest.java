package com.ticketsystem.models.requests.user;

import com.ticketsystem.models.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String name;
    String surname;
    String email;
    String password;
    String repeatPassword;
    Role role;
    String apartmentNumber;
    String roomNumber;

}
