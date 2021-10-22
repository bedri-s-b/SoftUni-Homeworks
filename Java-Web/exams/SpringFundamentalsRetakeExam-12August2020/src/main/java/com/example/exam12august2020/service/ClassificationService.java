package com.example.exam12august2020.service;


import com.example.exam12august2020.model.entity.ClassificationEntity;
import com.example.exam12august2020.model.entity.enums.ClassificationEnum;

public interface ClassificationService {
    void initClassifications();

    ClassificationEntity findByName(ClassificationEnum classification);
}
