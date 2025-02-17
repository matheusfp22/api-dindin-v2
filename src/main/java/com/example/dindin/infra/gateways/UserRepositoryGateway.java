package com.example.dindin.infra.gateways;

import com.example.dindin.core.entities.User;
import com.example.dindin.core.gateways.UserGateway;
import com.example.dindin.infra.persistence.UserEntity;
import com.example.dindin.infra.persistence.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        UserEntity saved = userRepository.save(userEntity);
        return userEntityMapper.toUSer(saved);
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        return userEntityMapper.toUSer(userEntity);
    }

}
