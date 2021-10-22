package com.example.exam12august2020.repository;

import com.example.exam12august2020.model.entity.ClassificationEntity;
import com.example.exam12august2020.model.entity.enums.ClassificationEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<ClassificationEntity ,Long> {

    ClassificationEntity findByClassificationName(ClassificationEnum classificationName);
}
