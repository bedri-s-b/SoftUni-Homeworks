package com.example.exam12august2020.model.service;

import com.example.exam12august2020.model.entity.ClassificationEntity;
import com.example.exam12august2020.model.entity.UserEntity;
import com.example.exam12august2020.model.entity.enums.ClassificationEnum;
import com.example.exam12august2020.model.entity.enums.ProgressEnum;

import java.time.LocalDate;

public class TaskServiceModel {

    private Long id;
    private String name;
    private String description;
    private ProgressEnum progress;
    private LocalDate dueDate;
    private ClassificationEnum classification;
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public TaskServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProgressEnum getProgress() {
        return progress;
    }

    public TaskServiceModel setProgress(ProgressEnum progress) {
        this.progress = progress;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskServiceModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }


    public UserEntity getUser() {
        return user;
    }

    public TaskServiceModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ClassificationEnum getClassification() {
        return classification;
    }

    public TaskServiceModel setClassification(ClassificationEnum classification) {
        this.classification = classification;
        return this;
    }
}
