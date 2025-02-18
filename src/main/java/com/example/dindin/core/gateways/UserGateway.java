package com.example.dindin.core.gateways;

import com.example.dindin.core.entities.User;

import java.util.Optional;

public interface UserGateway {

    User createUser(User user);

    Optional<User> findByEmail(String email);

    User AuthenticateUse(String email, String password);

}
