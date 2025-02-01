package com.tecsup.microservices.license_command_service.repository;

import com.tecsup.microservices.common_models.entity.DriverLicenseEntityPostgres;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface DriverLicenseRepository extends CrudRepository<DriverLicenseEntityPostgres, String> {
    void deleteByDueDateBefore(LocalDate date);

    @Modifying
    @Transactional
    @Query("UPDATE DriverLicenseEntityPostgres e SET e.state = false WHERE e.dueDate < :thresholdDate")
    void updateEstadoToZeroForDueDateBefore(LocalDate thresholdDate);
}
