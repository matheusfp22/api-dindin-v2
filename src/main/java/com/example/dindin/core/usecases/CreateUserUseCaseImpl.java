package com.example.dindin.core.usecases;

import com.example.dindin.core.entity.User;
import com.example.dindin.core.gateways.UserGateway;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User register(User user) {
        return userGateway.createUser(user);
    }

}
