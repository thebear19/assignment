package com.kasikornline.assignment.app.user.repository;

import com.kasikornline.assignment.app.user.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByName(String name);
}
