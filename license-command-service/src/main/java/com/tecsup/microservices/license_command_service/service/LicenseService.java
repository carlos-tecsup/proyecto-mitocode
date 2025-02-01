package com.tecsup.microservices.license_command_service.service;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTORequest;
import com.tecsup.microservices.common_models.dto.DriverLicenseDTOResponse;
import com.tecsup.microservices.common_models.dto.DriverLicenseDTOUpdateRequest;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenseService implements ILicenseService{
    private final DriverLicenseRepository driverLicenseRepository;

    private final UtilMapper utilMapper;
    private final UserRepository userRepository;
    private final KafkaUtil kafkaUtil;

    @PreAuthorize("hasRole('ADMIN')")

    public DriverLicenseDTOResponse createLicense(DriverLicenseDTORequest driverLicenseDTORequest){
        DriverLicenseEntityPostgres driverLicenseEntityPostgres = utilMapper.convertToEntity(driverLicenseDTORequest, DriverLicenseEntityPostgres.class);
        UserEntity userEntity = userRepository.findById(driverLicenseDTORequest.getUserId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        driverLicenseEntityPostgres.setUserEntity(userEntity);
        driverLicenseEntityPostgres.setCreateDate(LocalDateTime.now());
        DriverLicenseEntityPostgres driverLicenseEntityPostgresCreated = driverLicenseRepository.save(driverLicenseEntityPostgres);
        GenericEntity<DriverLicenseEntityMongo> driverLicenseEntityMongoGenericEntity = new GenericEntity<>(utilMapper.convertToEntity(driverLicenseEntityPostgresCreated, DriverLicenseEntityMongo.class),
                                                                                            DriverLicenseEntityMongo.class.getSimpleName());
        kafkaUtil.sendMessage(driverLicenseEntityMongoGenericEntity);

        return utilMapper.convertEntityToDTO(driverLicenseEntityPostgresCreated, DriverLicenseDTOResponse.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public DriverLicenseDTOResponse updateLicense(DriverLicenseDTOUpdateRequest driverLicenseDTOUpdateRequest) {

        DriverLicenseEntityPostgres driverLicenseEntityPostgresOld = driverLicenseRepository.findById(driverLicenseDTOUpdateRequest.getLicenseId())
                                                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "License not found"));;
        driverLicenseEntityPostgresOld.setDueDate(driverLicenseEntityPostgresOld.getDueDate().plusYears(1));
        driverLicenseEntityPostgresOld.setUpdateDate(LocalDateTime.now());
        DriverLicenseEntityPostgres driverLicenseEntityPostgresUpdated = driverLicenseRepository.save(driverLicenseEntityPostgresOld);
        GenericEntity<DriverLicenseEntityMongo> driverLicenseEntityMongoGenericEntity = new GenericEntity<>(utilMapper.convertToEntity(driverLicenseEntityPostgresUpdated, DriverLicenseEntityMongo.class),
                                                                                            DriverLicenseEntityMongo.class.getSimpleName());

        kafkaUtil.sendMessage(driverLicenseEntityMongoGenericEntity);

        return utilMapper.convertEntityToDTO(driverLicenseEntityPostgresUpdated, DriverLicenseDTOResponse.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public String deleteLicense() {

        LocalDate today = LocalDate.now();
        LocalDate thresholdDate = today.minusDays(30);
        driverLicenseRepository.updateEstadoToZeroForDueDateBefore(thresholdDate);

        kafkaUtil.sendMessage(new GenericEntity<>(null, String.class.getSimpleName()));
        return "Licenses deleted successfully";
    }


}
