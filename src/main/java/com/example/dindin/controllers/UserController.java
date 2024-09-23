package com.example.dindin.controllers;

import com.example.dindin.configurations.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String header) {
        String token = tokenService.validateToken(header.substring(7));

        return ResponseEntity.ok(token);
    }

}
