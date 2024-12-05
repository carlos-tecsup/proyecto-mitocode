package com.tecsup.microservices.license_command_service.service;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTO;
import com.tecsup.microservices.common_models.entity.DriverLicenseEntity;
import com.tecsup.microservices.common_models.entity.UserEntity;
import com.tecsup.microservices.common_models.util.UtilMapper;
import com.tecsup.microservices.license_command_service.repository.DriverLicenseRepository;
import com.tecsup.microservices.license_command_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenseService implements ILicenseService{
    private final DriverLicenseRepository driverLicenseRepository;
    private final UtilMapper utilMapper;
    private final UserRepository userRepository;

    public String createLicense(DriverLicenseDTO driverLicenseDTO){
        DriverLicenseEntity driverLicenseEntity = utilMapper.convertDTOtoEntity(driverLicenseDTO, DriverLicenseEntity.class);
        UserEntity userEntity = userRepository.findById(driverLicenseDTO.getUserId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        driverLicenseEntity.setUserEntity(userEntity);

        driverLicenseRepository.save(driverLicenseEntity);

        return "License emitted successfully";
    }
}
