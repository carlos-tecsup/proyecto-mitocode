package com.tecsup.microservices.license_command_service.service;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTORequest;
import com.tecsup.microservices.common_models.dto.DriverLicenseDTOResponse;
import com.tecsup.microservices.common_models.dto.DriverLicenseDTOUpdateRequest;

public interface ILicenseService {
     DriverLicenseDTOResponse createLicense(DriverLicenseDTORequest driverLicenseDTORequest);
     DriverLicenseDTOResponse updateLicense(DriverLicenseDTOUpdateRequest driverLicenseDTOUpdateRequest);

     String deleteLicense();
}
