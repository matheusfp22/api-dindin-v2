package com.example.dindin.core.usecases;

import com.example.dindin.core.entities.User;
import com.example.dindin.core.gateways.UserGateway;

public class CreateUserUseCase {

    private UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(User user) {
        return userGateway.createUser(user);
    }

}
