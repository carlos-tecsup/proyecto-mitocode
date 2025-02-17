package com.tecsup.microservices.licence_query_service.repository;

import com.tecsup.microservices.common_models.entity.DriverLicenseEntityPostgres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverLicensePostgresRepository extends CrudRepository<DriverLicenseEntityPostgres, Integer> {
}
