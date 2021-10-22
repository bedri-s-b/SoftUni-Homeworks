package com.example.exam12august2020.model.entity;

import com.example.exam12august2020.model.entity.enums.ClassificationEnum;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
public class ClassificationEntity extends BaseEntity{

    private ClassificationEnum classificationName;
    private String description;

    public ClassificationEntity() {
    }

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    public ClassificationEnum getClassificationName() {
        return classificationName;
    }

    public ClassificationEntity setClassificationName(ClassificationEnum classificationName) {
        this.classificationName = classificationName;
        return this;
    }

    @Column(columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public ClassificationEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
