package com.tecsup.microservices.common_models.dto;

import com.tecsup.microservices.common_models.enums.LicenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenseDTORequest {
    private LicenseCategory licenseCategory;
    private String fullName;
    private int documentNumber;
    private int userId;
    private String licenseId;

}