package com.tecsup.microservices.common_models.entity;

import jakarta.persistence.*;
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
    @Id
    private Integer id;
    private com.tecsup.microservices.common_models.enums.LicenseCategory LicenseCategory;
    private Date dueDate;
    private Date emitDate;
    private boolean state;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
}