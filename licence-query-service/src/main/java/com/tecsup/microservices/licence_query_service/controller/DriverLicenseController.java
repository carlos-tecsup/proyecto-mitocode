package com.tecsup.microservices.licence_query_service.controller;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTO;
import com.tecsup.microservices.licence_query_service.service.DriverLicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class DriverLicenseController {
    private final DriverLicenseService driverLicenseService;

    @GetMapping("/list")
    public ResponseEntity<List<DriverLicenseDTO>> listLicense() {

        return ResponseEntity.ok(driverLicenseService.listLicense());

    }
}
