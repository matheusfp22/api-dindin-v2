package com.example.dindin.infra.configurations.beans;

import com.example.dindin.core.gateways.UserGateway;
import com.example.dindin.core.usecases.user.AuthenticateUserUseCase;
import com.example.dindin.core.usecases.user.CreateUserUseCase;
import com.example.dindin.core.usecases.user.GetUserByEmailUseCase;
import com.example.dindin.infra.gateways.UserEntityMapper;
import com.example.dindin.infra.gateways.UserRepositoryGateway;
import com.example.dindin.infra.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Bean
    CreateUserUseCase createUserCase(UserGateway userGateway, GetUserByEmailUseCase getUserByEmailUseCase) {
        return new CreateUserUseCase(userGateway, getUserByEmailUseCase);
    }

    @Bean
    AuthenticateUserUseCase authenticateUserUseCase(UserGateway userGateway, GetUserByEmailUseCase getUserByEmailUseCase) {
        return new AuthenticateUserUseCase(userGateway, getUserByEmailUseCase);
    }

    @Bean
    GetUserByEmailUseCase getUserByEmailUseCase(UserGateway userGateway) {
        return new GetUserByEmailUseCase(userGateway);
    }

}
