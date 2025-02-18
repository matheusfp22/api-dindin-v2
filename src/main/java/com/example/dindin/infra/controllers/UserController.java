package com.example.dindin.infra.controllers;

import com.example.dindin.core.entities.User;
import com.example.dindin.core.usecases.user.AuthenticateUserUseCase;
import com.example.dindin.core.usecases.user.CreateUserUseCase;
import com.example.dindin.infra.dtos.requests.UserLoginRequestDTO;
import com.example.dindin.infra.dtos.requests.UserRegisterRequestDTO;
import com.example.dindin.infra.dtos.responses.ResponseDTO;
import com.example.dindin.infra.gateways.UserEntityMapper;
import com.example.dindin.infra.persistence.UserEntity;
import com.example.dindin.infra.configurations.security.TokenService;
import com.example.dindin.infra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final UserDtoMapper userDTOMapper;
    private final UserEntityMapper userEntityMapper;

    public UserController(CreateUserUseCase createUserUseCase, AuthenticateUserUseCase authenticateUserUseCase, UserDtoMapper userDTOMapper, UserEntityMapper userEntityMapper) {
        this.createUserUseCase = createUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.userDTOMapper = userDTOMapper;
        this.userEntityMapper = userEntityMapper;
    }

    @GetMapping
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(userService.validateToken(header));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserLoginRequestDTO request) {
        try {
            User userDomain = authenticateUserUseCase.execute(request.email(), request.password());
            UserEntity userEntity = userEntityMapper.toEntity(userDomain);
            String token = userService.generateToken(userEntity);

            return ResponseEntity.ok(new ResponseDTO("Login successful", token, true));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(new ResponseDTO(exception.getMessage(), null, false));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody UserRegisterRequestDTO request) {
        try {
            User userDomain = userDTOMapper.toUser(request);
            User user = createUserUseCase.execute(userDomain);

            //UserEntity user = userService.register(body.name(), body.email(), body.password());
            //String token = userService.generateToken(user);
            //return ResponseEntity.ok(new ResponseDTO("Registration successful", token, true));

            return ResponseEntity.ok(new ResponseDTO("Registration successful", user, true));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(new ResponseDTO(exception.getMessage(), null, false));
        }
    }

}
