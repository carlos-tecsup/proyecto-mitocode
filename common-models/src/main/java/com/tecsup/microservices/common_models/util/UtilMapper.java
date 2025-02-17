package com.tecsup.microservices.common_models.util;

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
    public  <T, U> T convertToEntity(U source, Class<T> targetClass) {
        try {
            // Crear una nueva instancia de la clase de destino
            T target = targetClass.getDeclaredConstructor().newInstance();
            // Copiar propiedades desde la fuente al destino
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Error converting entity: " + e.getMessage(), e);
        }
    }

    public <E, D> D convertEntityToDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Error converting Entity to DTO", e);
        }
    }


}
