package com.tecsup.microservices.authentication_server_jwt.service;

import com.tecsup.microservices.authentication_server_jwt.config.security.JwtService;

import com.tecsup.microservices.authentication_server_jwt.service.repository.UserRepository;
import com.tecsup.microservices.common_models.dto.UserCredentials;
import com.tecsup.microservices.common_models.dto.UserDTOResponse;
import com.tecsup.microservices.common_models.dto.UserRegister;
import com.tecsup.microservices.common_models.dto.AuthDTOResponse;
import com.tecsup.microservices.common_models.entity.UserEntity;
import com.tecsup.microservices.common_models.util.UtilMapper;
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
    private final UtilMapper utilMapper;


    public AuthDTOResponse register(UserRegister userRegister) {
        UserEntity entity = UserEntity.builder()
                .username(userRegister.username())
                .password(passwordEncoder.encode(userRegister.password()))
                .email(userRegister.email())
                .roles(userRegister.roles())
                .name(userRegister.name())
                .lastname(userRegister.lastname())
                .build();

        UserEntity userEntity = userRepository.save(entity);
        UserDTOResponse userDTOResponse = utilMapper.convertToEntity(userEntity, UserDTOResponse.class);
        String token = jwtService.generateToken(userEntity);
        AuthDTOResponse authDTOResponse = AuthDTOResponse.builder().user(userDTOResponse).build();
        authDTOResponse.getUser().setToken(token);
        return authDTOResponse;

    }

    public AuthDTOResponse authenticate(UserCredentials userCredentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userCredentials.username(), userCredentials.password()));


        UserEntity userEntity = userRepository.findByUsername(userCredentials.username()).orElse(null);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuario no registrado en la BD");
        }

        UserDTOResponse userDTOResponse = utilMapper.convertToEntity(userEntity, UserDTOResponse.class);
        String token = jwtService.generateToken(userEntity);
        AuthDTOResponse authDTOResponse = AuthDTOResponse.builder().user(userDTOResponse).build();
        authDTOResponse.getUser().setToken(token);
        return authDTOResponse;
    }

}
