package com.example.exam_web.repository;

import com.example.exam_web.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
