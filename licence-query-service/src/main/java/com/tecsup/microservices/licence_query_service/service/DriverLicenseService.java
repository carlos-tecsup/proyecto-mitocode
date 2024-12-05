package com.tecsup.microservices.licence_query_service.service;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTO;
import com.tecsup.microservices.licence_query_service.repository.DriverLicenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;

    public List<DriverLicenseDTO> listLicense(){
        // Convertir a List usando Streams
        return StreamSupport.stream(driverLicenseRepository.findAll().spliterator(), false)
                .map(driverLicenseEntity -> {
                    DriverLicenseDTO driverLicenseDTO = DriverLicenseDTO.builder().build();
                    BeanUtils.copyProperties(driverLicenseEntity, driverLicenseDTO);
                    return driverLicenseDTO;
                }).collect(Collectors.toList());

    }
}
