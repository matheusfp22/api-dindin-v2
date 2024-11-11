package com.example.dindin.infra.controllers;

import com.example.dindin.infra.dtos.LoginRequestDTO;
import com.example.dindin.infra.dtos.RegisterRequestDTO;
import com.example.dindin.infra.dtos.ResponseDTO;
import com.example.dindin.infra.entity.User;
import com.example.dindin.infra.configurations.security.TokenService;
import com.example.dindin.infra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(userService.validateToken(header));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        try {
            User user = userService.login(body.email(), body.password());
            String token = userService.generateToken(user);

            return ResponseEntity.ok(new ResponseDTO("Login successful", token, true));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(new ResponseDTO(exception.getMessage(), null, false));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        try {
            User user = userService.register(body.name(), body.email(), body.password());
            String token = userService.generateToken(user);

            return ResponseEntity.ok(new ResponseDTO("Registration successful", token, true));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(new ResponseDTO(exception.getMessage(), null, false));
        }
    }

}
