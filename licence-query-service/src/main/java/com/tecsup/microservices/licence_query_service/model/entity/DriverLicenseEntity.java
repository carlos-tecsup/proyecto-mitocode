package com.tecsup.microservices.licence_query_service.model.entity;

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
@Entity
@Table(name = "driver_license")
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LicenseCategory LicenseCategory;
    private Date dueDate;
    private Date emitDate;
    private boolean estado;
}