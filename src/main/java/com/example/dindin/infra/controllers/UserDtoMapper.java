package com.example.dindin.infra.controllers;

import com.example.dindin.core.entities.User;
import com.example.dindin.infra.dtos.requests.UserRegisterRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    UserRegisterRequestDTO toDto(User user) {
        return new UserRegisterRequestDTO(user.getName(), user.getEmail(), user.getPassword());
    }

    User toUser(UserRegisterRequestDTO user) {
        return new User(user.name(), user.email(), user.password());
    }

}
