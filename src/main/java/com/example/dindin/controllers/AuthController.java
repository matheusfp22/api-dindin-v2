package com.example.dindin.controllers;

import com.example.dindin.dto.LoginRequestDTO;
import com.example.dindin.dto.RegisterRequestDTO;
import com.example.dindin.dto.ResponseDTO;
import com.example.dindin.entity.User;
import com.example.dindin.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        try {
            User user = authService.login(body.email(), body.password());
            String token = authService.generateToken(user);

            return ResponseEntity.ok(new ResponseDTO("Login successful", token, true));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(new ResponseDTO(exception.getMessage(), null, false));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        try {
            User user = authService.register(body.name(), body.email(), body.password());
            String token = authService.generateToken(user);

            return ResponseEntity.ok(new ResponseDTO("Registration successful", token, true));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(new ResponseDTO(exception.getMessage(), null, false));
        }
    }

}
