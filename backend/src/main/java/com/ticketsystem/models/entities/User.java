package com.ticketsystem.models.entities;

import com.ticketsystem.models.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity implements GrantedAuthority {
    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column
    @Enumerated(EnumType.STRING)
    Role role;

    @Column(name = "apartment_number")
    String apartmentNumber;

    @Column(name = "room_number")
    String roomNumber;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
