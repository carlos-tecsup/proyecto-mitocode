package com.tecsup.microservices.authentication_server_jwt.service;

import com.tecsup.microservices.authentication_server_jwt.config.security.JwtService;

import com.tecsup.microservices.authentication_server_jwt.service.repository.UserRepository;
import com.tecsup.microservices.common_models.dto.UserCredentials;
import com.tecsup.microservices.common_models.dto.UserRegister;
import com.tecsup.microservices.common_models.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public String register(UserRegister userRegister) {
        UserEntity entity = UserEntity.builder()
                .username(userRegister.username())
                .password(passwordEncoder.encode(userRegister.password()))
                .email(userRegister.email())
                .roles(userRegister.roles())
                .name(userRegister.name())
                .lastname(userRegister.lastname())
                .build();

        userRepository.save(entity);
        return jwtService.generateToken(entity);

    }

    public String authenticate(UserCredentials userCredentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userCredentials.username(), userCredentials.password()));


        UserEntity userEntity = userRepository.findByUsername(userCredentials.username()).orElse(null);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuario no registrado en la BD");
        }

        return jwtService.generateToken(userEntity);

    }

}
