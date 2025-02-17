package com.tecsup.microservices.licence_query_service.service;

import com.tecsup.microservices.common_models.entity.DriverLicenseEntityMongo;
import com.tecsup.microservices.common_models.enums.LicenseCategory;
import com.tecsup.microservices.licence_query_service.repository.DriverLicenseMongoRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverLicenseCustomRepositoryImpl implements DriverLicenseMongoRepositoryCustom {
    @Autowired
    private final MongoTemplate mongoTemplate;

    @Override
    public List<DriverLicenseEntityMongo> findDriverLicenseEntityMongoByFilters(String idLicense, Boolean isValidate, LicenseCategory licenseCategory) {
        Query query = new Query();

        if (idLicense != null) {
            query.addCriteria(Criteria.where("id").is(idLicense));
        }
        if (isValidate != null) {
            query.addCriteria(Criteria.where("state").is(isValidate));
        }
        if (licenseCategory != null) {
            query.addCriteria(Criteria.where("licenseCategory").is(licenseCategory.name()));
        }

        return mongoTemplate.find(query, DriverLicenseEntityMongo.class);
    }
}
