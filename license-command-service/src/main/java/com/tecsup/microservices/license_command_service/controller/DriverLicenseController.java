package com.tecsup.microservices.license_command_service.controller;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTORequest;
import com.tecsup.microservices.license_command_service.service.ILicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class DriverLicenseController {
    private  final ILicenseService iLicenseService;

    @PostMapping("/create")
    public ResponseEntity<String> createLicense(@RequestBody DriverLicenseDTORequest driverLicenseDTORequest){
        String response = iLicenseService.createLicense(driverLicenseDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateLicense(@RequestBody DriverLicenseDTORequest driverLicenseDTORequest){
        String response = iLicenseService.updateLicense(driverLicenseDTORequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
