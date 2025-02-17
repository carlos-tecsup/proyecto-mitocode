package com.tecsup.microservices.common_models.entity;

import com.tecsup.microservices.common_models.enums.LicenseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "driver_license")
@AllArgsConstructor
public class DriverLicenseEntityPostgres extends AuditFields implements Serializable  {

    @Serial
    private static final long serialVersionUID = 1234567890L;

    @jakarta.persistence.Id
    @GeneratedValue(generator = "seq-gen")
    @GenericGenerator(name = "seq-gen", strategy = "com.tecsup.microservices.common_models.secuence.SequentialStringIdGeneratorLicense")
    private String id;
    @Enumerated(EnumType.STRING)
    private LicenseCategory licenseCategory;
    private boolean state;
    private int documentNumber;
    private String fullName;
    private LocalDate dueDate;
    private LocalDate emitDate;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    public DriverLicenseEntityPostgres() {
        this.dueDate = LocalDate.now().plusYears(3);
        this.emitDate = LocalDate.now();
        this.state = true;
    }
}