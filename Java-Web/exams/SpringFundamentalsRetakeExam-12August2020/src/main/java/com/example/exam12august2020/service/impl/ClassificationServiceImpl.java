package com.example.exam12august2020.service.impl;

import com.example.exam12august2020.model.entity.ClassificationEntity;
import com.example.exam12august2020.model.entity.enums.ClassificationEnum;
import com.example.exam12august2020.repository.ClassificationRepository;
import com.example.exam12august2020.service.ClassificationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void initClassifications() {
        if (classificationRepository.count() != 0){
            return;
        }
        ClassificationEnum[] classifications = ClassificationEnum.values();

        Arrays.stream(classifications).forEach(c -> {
            ClassificationEntity classification = new ClassificationEntity();
            classification.setClassificationName(c).setDescription(c.name());
            classificationRepository.save(classification);
        });
    }

    @Override
    public ClassificationEntity findByName(ClassificationEnum classification) {
       return
        classificationRepository.findByClassificationName(classification);
    }


}
