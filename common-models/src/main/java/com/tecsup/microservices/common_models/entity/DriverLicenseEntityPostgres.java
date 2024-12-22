package com.tecsup.microservices.common_models.entity;

import com.tecsup.microservices.common_models.enums.LicenseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "driver_license")
@AllArgsConstructor
public class DriverLicenseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private LicenseCategory licenseCategory;
    private boolean state;
    private int documentNumber;
    private String fullName;
    private LocalDate dueDate;
    private LocalDate emitDate;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    public DriverLicenseEntity() {
        this.dueDate = LocalDate.now();
        this.emitDate = LocalDate.now().plusYears(3);
        this.state = true;
    }
}