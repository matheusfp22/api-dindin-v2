package com.example.dindin.core.usecases.user;

import com.example.dindin.core.entities.User;
import com.example.dindin.core.gateways.UserGateway;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

public class CreateUserUseCase {

    private UserGateway userGateway;
    private GetUserByEmailUseCase getUserByEmailUseCase;

    public CreateUserUseCase(UserGateway userGateway, GetUserByEmailUseCase getUserByEmailUseCase) {
        this.userGateway = userGateway;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    public User execute(User user) {
        Optional<User> existingUser = getUserByEmailUseCase.execute(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        User newUser = new User(user.getName(), user.getEmail(), hashedPassword);

        return userGateway.createUser(newUser);
    }

}
