package com.ticketsystem.controllers;

import com.ticketsystem.models.requests.user.UserAuthRequest;
import com.ticketsystem.models.requests.user.UserCreateRequest;
import com.ticketsystem.services.user.UserReadService;
import com.ticketsystem.services.user.UserWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserReadService userReadService;
    private final UserWriteService userWriteService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthRequest request){
        return new ResponseEntity<>(userWriteService.login(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(userReadService.getAll(),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserCreateRequest request){
        return new ResponseEntity<>(userWriteService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        return new ResponseEntity<>(userReadService.getProfile(), HttpStatus.OK);
    }
}
