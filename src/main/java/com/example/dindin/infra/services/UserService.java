package com.example.dindin.infra.services;

import com.example.dindin.infra.configurations.security.TokenService;
import com.example.dindin.infra.persistence.UserEntity;
import com.example.dindin.infra.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UserEntity login(String email, String password) {
        UserEntity user = this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = this.tokenService.generateToken(user);

            return user;
        }

        throw new RuntimeException("Invalid password");
    }

    public String generateToken(UserEntity user) {
        return tokenService.generateToken(user);
    }

    public String validateToken(@RequestHeader("Authorization") String header) {
        return tokenService.validateToken(header.substring(7));
    }

}
