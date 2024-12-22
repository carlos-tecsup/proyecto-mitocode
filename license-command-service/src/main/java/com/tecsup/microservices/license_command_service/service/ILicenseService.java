package com.tecsup.microservices.license_command_service.service;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTORequest;

public interface ILicenseService {
     String createLicense(DriverLicenseDTORequest driverLicenseDTORequest);
     String updateLicense(DriverLicenseDTORequest driverLicenseDTORequest);
}
