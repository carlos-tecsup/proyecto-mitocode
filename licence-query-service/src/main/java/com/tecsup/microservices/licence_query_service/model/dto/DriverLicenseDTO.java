package com.tecsup.microservices.licence_query_service.model.dto;

import com.tecsup.microservices.licence_query_service.model.enums.LicenseCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
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