package com.example.exam12august2020.repository;

import com.example.exam12august2020.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findByEmailAndPassword(String username, String password);
}
