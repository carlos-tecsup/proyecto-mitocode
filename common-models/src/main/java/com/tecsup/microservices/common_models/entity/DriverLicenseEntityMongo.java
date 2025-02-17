package com.tecsup.microservices.common_models.entity;

import com.tecsup.microservices.common_models.enums.LicenseCategory;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "driver_license")
public class DriverLicenseEntityMongo extends AuditFields {

    @Id
    private String id;
    private LicenseCategory licenseCategory;
    private boolean state;
    private int documentNumber;
    private String fullName;
    private LocalDate dueDate;
    private LocalDate emitDate;

    public DriverLicenseEntityMongo() {
        this.dueDate = LocalDate.now().plusYears(3);
        this.emitDate = LocalDate.now();
        this.state = true;
    }
}
