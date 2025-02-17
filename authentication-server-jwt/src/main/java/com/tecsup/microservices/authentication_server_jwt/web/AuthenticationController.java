package com.tecsup.microservices.authentication_server_jwt.web;

import com.tecsup.microservices.authentication_server_jwt.service.AuthService;
import com.tecsup.microservices.common_models.dto.UserCredentials;
import com.tecsup.microservices.common_models.dto.AuthDTOResponse;
import com.tecsup.microservices.common_models.dto.UserRegister;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @Operation(
            summary = "Register a new user",
            description = "Registers a user by providing a username and password.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRegister.class),
                            examples = @ExampleObject(
                                    name = "Default Example",
                                    summary = "Register Example",
                                    value = "{\n" +
                                            "    \"name\": \"Jaime\",\n" +
                                            "    \"lastname\": \"Mediga \",\n" +
                                            "    \"username\": \"jmedina\",\n" +
                                            "    \"email\": \"jmedina@gmail.com\",\n" +
                                            "    \"password\": \"pass123\",\n" +
                                            "    \"roles\": [\n" +
                                            "        \"ROLE_ADMIN\"\n" +
                                            "    ]\n" +
                                            "}"
                            )
                    )
            )
    )
    @PostMapping("/register")
    public ResponseEntity<AuthDTOResponse> register(@RequestBody UserRegister userRegister) {
        return ResponseEntity.ok(authService.register(userRegister));
    }


    @PostMapping("/authenticate")
    @Operation(
            summary = "Authenticate user",
            description = "Authenticate a user with username and password.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserCredentials.class),
                            examples = @ExampleObject(
                                    name = "Default Example",
                                    summary = "Login Example",
                                    value = "{ \"username\": \"jmedina\", \"password\": \"pass123\" }"
                            )
                    )
            )
    )
    public ResponseEntity<AuthDTOResponse> login(@RequestBody UserCredentials userCredentials) {
        return ResponseEntity.ok(authService.authenticate(userCredentials));
    }


}
