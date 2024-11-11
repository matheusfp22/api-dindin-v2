package com.example.dindin.infra.services;

import com.example.dindin.infra.configurations.security.TokenService;
import com.example.dindin.infra.entity.User;
import com.example.dindin.infra.repositories.UserRepository;
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

    public User login(String email, String password) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = this.tokenService.generateToken(user);

            return user;
        }

        throw new RuntimeException("Invalid password");
    }

    public User register(String name, String email, String password) {
        Optional<User> user = this.userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setName(name);

        return userRepository.save(newUser);
    }

    public String generateToken(User user) {
        return tokenService.generateToken(user);
    }

    public String validateToken(@RequestHeader("Authorization") String header) {
        return tokenService.validateToken(header.substring(7));
    }

}
