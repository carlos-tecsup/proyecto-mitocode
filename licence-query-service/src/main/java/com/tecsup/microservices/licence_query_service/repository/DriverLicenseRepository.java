package com.tecsup.microservices.licence_query_service.repository;

import com.tecsup.microservices.common_models.entity.DriverLicenseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverLicenseRepository extends CrudRepository<DriverLicenseEntity, Integer> {
}
