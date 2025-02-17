package com.example.dindin.infra.gateways;

import com.example.dindin.core.entities.User;
import com.example.dindin.infra.persistence.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    UserEntity toEntity(User user) {
        return new UserEntity(user.getName(),  user.getEmail(), user.getPassword());
    }

    User toUSer(UserEntity UserEntity) {
        return new User(UserEntity.getName(),  UserEntity.getEmail(), UserEntity.getPassword());
    }

}
