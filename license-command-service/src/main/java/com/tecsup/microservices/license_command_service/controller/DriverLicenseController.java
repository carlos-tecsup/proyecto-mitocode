package com.tecsup.microservices.license_command_service.controller;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTO;
import com.tecsup.microservices.common_models.entity.DriverLicenseEntity;
import com.tecsup.microservices.license_command_service.service.ILicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class DriverLicenseController {
    private  final ILicenseService iLicenseService;

    @PostMapping("/createLicense")
    public ResponseEntity<String> createLicense(@RequestBody DriverLicenseDTO driverLicenseDTO){
        return ResponseEntity.ok(iLicenseService.createLicense(driverLicenseDTO));
    }
}
