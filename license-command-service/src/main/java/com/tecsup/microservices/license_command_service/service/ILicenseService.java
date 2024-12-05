package com.tecsup.microservices.license_command_service.service;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTO;
import com.tecsup.microservices.common_models.entity.DriverLicenseEntity;

public interface ILicenseService {
     String createLicense(DriverLicenseDTO driverLicenseDTO);
}
