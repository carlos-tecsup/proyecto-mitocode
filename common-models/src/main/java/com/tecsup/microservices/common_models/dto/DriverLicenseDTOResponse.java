package com.tecsup.microservices.common_models.dto;

import com.tecsup.microservices.common_models.enums.LicenseCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenseDTOResponse {
    private LicenseCategory licenseCategory;
    private String fullName;
    private int documentNumber;
    private String id;
    private LocalDate dueDate;
    private LocalDate emitDate;
}
