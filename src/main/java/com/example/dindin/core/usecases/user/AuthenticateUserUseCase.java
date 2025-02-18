package com.example.dindin.core.usecases.user;

import com.example.dindin.core.entities.User;
import com.example.dindin.core.gateways.UserGateway;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

public class AuthenticateUserUseCase {

    private UserGateway userGateway;
    private GetUserByEmailUseCase getUserByEmailUseCase;

    public AuthenticateUserUseCase(UserGateway userGateway, GetUserByEmailUseCase getUserByEmailUseCase) {
        this.userGateway = userGateway;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    public User execute(String email, String password) {
        Optional<User> userOptional = getUserByEmailUseCase.execute(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

}
