package com.ticketsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world!";
    }
}
