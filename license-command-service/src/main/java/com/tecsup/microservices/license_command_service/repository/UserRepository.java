package com.tecsup.microservices.license_command_service.repository;

import com.tecsup.microservices.common_models.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
