package com.tecsup.microservices.license_command_service.repository;

import com.tecsup.microservices.common_models.entity.DriverLicenseEntityPostgres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverLicenseRepository extends CrudRepository<DriverLicenseEntityPostgres, String> {
}
