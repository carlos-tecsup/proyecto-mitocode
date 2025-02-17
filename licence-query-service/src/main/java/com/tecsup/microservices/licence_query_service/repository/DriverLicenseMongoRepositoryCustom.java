package com.tecsup.microservices.licence_query_service.repository;

import com.tecsup.microservices.common_models.entity.DriverLicenseEntityMongo;
import com.tecsup.microservices.common_models.enums.LicenseCategory;

import java.util.List;

public interface DriverLicenseMongoRepositoryCustom {
    List<DriverLicenseEntityMongo> findDriverLicenseEntityMongoByFilters(String idLicense, Boolean isValidate, LicenseCategory licenseCategory);

}
