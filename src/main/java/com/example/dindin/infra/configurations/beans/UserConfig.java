package com.example.dindin.infra.configurations.beans;

import com.example.dindin.core.gateways.UserGateway;
import com.example.dindin.core.usecases.CreateUserUseCase;
import com.example.dindin.infra.gateways.UserEntityMapper;
import com.example.dindin.infra.gateways.UserRepositoryGateway;
import com.example.dindin.infra.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CreateUserUseCase createUserCase(UserGateway userGateway) {
        return new CreateUserUseCase(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
    }

}
