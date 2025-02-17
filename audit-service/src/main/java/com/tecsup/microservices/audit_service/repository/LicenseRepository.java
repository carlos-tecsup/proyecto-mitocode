package com.tecsup.microservices.audit_service.repository;

import com.tecsup.microservices.common_models.entity.DriverLicenseEntityMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LicenseRepository extends MongoRepository<DriverLicenseEntityMongo, String > {

}

