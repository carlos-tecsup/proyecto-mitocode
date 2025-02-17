package com.tecsup.microservices.licence_query_service.service;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTOResponse;
import com.tecsup.microservices.common_models.enums.LicenseCategory;
import com.tecsup.microservices.licence_query_service.repository.DriverLicensePostgresRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverLicenseService {
    private final DriverLicensePostgresRepository driverLicensePostgresRepository;
    private final DriverLicenseCustomRepositoryImpl driverLicenseCustomRepositoryImpl;

    public List<DriverLicenseDTOResponse> listLicense(){
        // Convertir a List usando Streams
        return StreamSupport.stream(driverLicensePostgresRepository.findAll().spliterator(), false)
                .map(driverLicenseEntity -> {
                    DriverLicenseDTOResponse driverLicenseDTOResponse = DriverLicenseDTOResponse.builder().build();
                    BeanUtils.copyProperties(driverLicenseEntity, driverLicenseDTOResponse);
                    return driverLicenseDTOResponse;
                }).collect(Collectors.toList());

    }

    public List<DriverLicenseDTOResponse> filterLicense(Boolean isValidate, LicenseCategory licenseCategory, String idLicense) {
       if(isValidate == null && licenseCategory == null && idLicense == null) {
           return Collections.emptyList();
       }
        return driverLicenseCustomRepositoryImpl.findDriverLicenseEntityMongoByFilters(idLicense, isValidate, licenseCategory)
                .stream().map(driverLicenseEntityMongo -> {
                    DriverLicenseDTOResponse driverLicenseDTOResponse = DriverLicenseDTOResponse.builder().build();
                    BeanUtils.copyProperties(driverLicenseEntityMongo, driverLicenseDTOResponse);
                    return driverLicenseDTOResponse;
                }).collect(Collectors.toList());
    }

}
