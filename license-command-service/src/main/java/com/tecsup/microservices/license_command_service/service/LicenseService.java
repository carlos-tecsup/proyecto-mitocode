package com.tecsup.microservices.license_command_service.service;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTORequest;
import com.tecsup.microservices.common_models.entity.DriverLicenseEntityMongo;
import com.tecsup.microservices.common_models.entity.DriverLicenseEntityPostgres;
import com.tecsup.microservices.common_models.entity.GenericEntity;
import com.tecsup.microservices.common_models.entity.UserEntity;
import com.tecsup.microservices.common_models.util.UtilMapper;
import com.tecsup.microservices.license_command_service.repository.DriverLicenseRepository;
import com.tecsup.microservices.license_command_service.repository.UserRepository;
import com.tecsup.microservices.license_command_service.util.KafkaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenseService implements ILicenseService{
    private final DriverLicenseRepository driverLicenseRepository;
    private final UtilMapper utilMapper;
    private final UserRepository userRepository;
    private final KafkaUtil kafkaUtil;

    public String createLicense(DriverLicenseDTORequest driverLicenseDTORequest){
        DriverLicenseEntityPostgres driverLicenseEntityPostgres = utilMapper.convertToEntity(driverLicenseDTORequest, DriverLicenseEntityPostgres.class);
        UserEntity userEntity = userRepository.findById(driverLicenseDTORequest.getUserId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        driverLicenseEntityPostgres.setUserEntity(userEntity);

        DriverLicenseEntityPostgres driverLicenseEntityPostgresCreated = driverLicenseRepository.save(driverLicenseEntityPostgres);
        GenericEntity<DriverLicenseEntityMongo> driverLicenseEntityMongoGenericEntity = new GenericEntity<>(utilMapper.convertToEntity(driverLicenseEntityPostgresCreated, DriverLicenseEntityMongo.class),
                                                                                            DriverLicenseEntityMongo.class.getSimpleName());
        kafkaUtil.sendMessage(driverLicenseEntityMongoGenericEntity);

        return "License emitted successfully";
    }

    @Override
    public String updateLicense(DriverLicenseDTORequest driverLicenseDTORequest) {
        DriverLicenseEntityPostgres driverLicenseEntityPostgresOld = driverLicenseRepository.findById(driverLicenseDTORequest.getLicenseId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "License not found"));;
        driverLicenseEntityPostgresOld.setDueDate(driverLicenseEntityPostgresOld.getDueDate().plusYears(1));

        DriverLicenseEntityPostgres driverLicenseEntityPostgresUpdated = driverLicenseRepository.save(driverLicenseEntityPostgresOld);
        GenericEntity<DriverLicenseEntityMongo> driverLicenseEntityMongoGenericEntity = new GenericEntity<>(utilMapper.convertToEntity(driverLicenseEntityPostgresUpdated, DriverLicenseEntityMongo.class),
                                                                                            DriverLicenseEntityMongo.class.getSimpleName());
        kafkaUtil.sendMessage(driverLicenseEntityMongoGenericEntity);

        return "License updated successfully";
    }


}
