package com.tecsup.microservices;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class Controller {

    @GetMapping("/test")
    public String testEndpoint() {
        return "Swagger is working!";
    }
}