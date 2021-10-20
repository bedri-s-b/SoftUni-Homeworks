package com.example.exam.repository;

import com.example.exam.model.entity.UserEntity;
import com.example.exam.model.service.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Pattern;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    boolean existsByUsername(String username);

  Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
