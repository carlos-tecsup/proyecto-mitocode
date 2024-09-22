package com.tecsup.microservices.common_models.dto;

import com.tecsup.microservices.common_models.enums.LicenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenseDTO {

    private LicenseCategory LicenseCategory;
    private Date dueDate;
    private Date emitDate;
    private boolean estado;
}