package com.example.dindin.core.gateways;

import com.example.dindin.core.entities.User;

public interface UserGateway {

    User createUser(User user);

    User findByEmail(String email);

}
