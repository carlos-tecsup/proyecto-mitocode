package com.tecsup.microservices.licence_query_service.repository;

import com.tecsup.microservices.common_models.entity.DriverLicenseEntityMongo;
import com.tecsup.microservices.common_models.entity.DriverLicenseEntityPostgres;
import com.tecsup.microservices.common_models.enums.LicenseCategory;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DriverLicenseMongoRepository extends MongoRepository<DriverLicenseEntityMongo, String> {



}
