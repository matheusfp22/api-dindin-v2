package com.example.dindin.core.usecases.user;

import com.example.dindin.core.entities.User;
import com.example.dindin.core.gateways.UserGateway;

import java.util.Optional;

public class GetUserByEmailUseCase {

    private UserGateway userGateway;

    public GetUserByEmailUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public Optional<User> execute(String email) {
        return userGateway.findByEmail(email);
    }

}
