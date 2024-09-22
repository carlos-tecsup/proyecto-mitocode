package com.tecsup.microservices.authentication_server_jwt.service.repository;

import com.tecsup.microservices.common_models.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

}
