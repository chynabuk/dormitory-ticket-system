package com.ticketsystem.services.user.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ticketsystem.exceptions.impl.PasswordNotMatchingException;
import com.ticketsystem.exceptions.impl.ResourceDuplicateException;
import com.ticketsystem.models.dtos.UserAuthDto;
import com.ticketsystem.models.dtos.UserDetailDto;
import com.ticketsystem.models.dtos.UserDto;
import com.ticketsystem.models.entities.User;
import com.ticketsystem.models.mappers.UserMapper;
import com.ticketsystem.models.requests.user.UserAuthRequest;
import com.ticketsystem.models.requests.user.UserCreateRequest;
import com.ticketsystem.repositories.UserRepository;
import com.ticketsystem.services.user.UserReadService;
import com.ticketsystem.services.user.UserWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserWriteServiceImpl implements UserWriteService {
    private final UserUsernameFinderService userUsernameFinderService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private static final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    @Override
    public UserAuthDto login(UserAuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailDto userDetailDTO = (UserDetailDto) authentication.getPrincipal();
        Collection<GrantedAuthority> claim = (Collection<GrantedAuthority>) userDetailDTO.getAuthorities();
        List<String> grantedAuthorities = claim.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String accessToken = JWT.create()
                .withSubject(userDetailDTO.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)))
                .withIssuer("http:localhost:8080/sign-in")
                .withClaim("roles", grantedAuthorities)
                .sign(algorithm);

        User user = userDetailDTO.getUser();
        return new UserAuthDto(accessToken, user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole().name());
    }

    @Override
    public UserDto createUser(UserCreateRequest request) {
        return userMapper.apply(userRepository.save(create(request)));
    }

    private User create(UserCreateRequest request){
        User user = userUsernameFinderService.findByEmail(request.getEmail());
        if (user != null){
            throw new ResourceDuplicateException("User " + request.getEmail() + " is already existed");
        }

        System.out.println(request.getPassword());
        System.out.println(request.getRepeatPassword());
        if (!request.getPassword().equals(request.getRepeatPassword())){
            throw new PasswordNotMatchingException("Passwords are not matched");
        }

        return User.builder()
                .firstName(request.getName())
                .lastName(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .apartmentNumber(request.getApartmentNumber())
                .roomNumber(request.getRoomNumber())
                .build();
    }
}
