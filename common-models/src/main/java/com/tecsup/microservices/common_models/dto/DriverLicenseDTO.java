package com.tecsup.microservices.common_models.dto;

import com.tecsup.microservices.common_models.enums.LicenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenseDTO {
    private LicenseCategory licenseCategory;
    private String fullName;
    private int documentNumber;
    private LocalDate dueDate;
    private LocalDate emitDate;
    private boolean state;
    private int userId = 13;

}