package com.tecsup.microservices.common_models.util;

import com.tecsup.microservices.common_models.dto.DriverLicenseDTO;
import com.tecsup.microservices.common_models.entity.DriverLicenseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UtilMapper {

    public <D, E> E convertDTOtoEntity(D dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error converting DTO to Entity", e);
        }
    }

}
