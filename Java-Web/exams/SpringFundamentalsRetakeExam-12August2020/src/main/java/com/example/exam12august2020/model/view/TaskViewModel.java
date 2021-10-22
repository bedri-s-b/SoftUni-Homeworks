package com.example.exam12august2020.model.view;

import com.example.exam12august2020.model.entity.enums.ClassificationEnum;
import com.example.exam12august2020.model.entity.enums.ProgressEnum;

import java.time.LocalDate;

public class TaskViewModel {

    private Long id;
    private String name;
    private String assignedTo;
    private ClassificationEnum classificationEnum;
    private LocalDate dueDate;
    private ProgressEnum progress;

    public TaskViewModel(Long id,String name, String assignedTo, ClassificationEnum classificationEnum, LocalDate dueDate, ProgressEnum progress) {
        this.id = id;
        this.name = name;
        this.assignedTo = assignedTo;
        this.classificationEnum = classificationEnum;
        this.dueDate = dueDate;
        this.progress = progress;
    }

    public TaskViewModel() {
    }

    public String getName() {
        return name;
    }

    public TaskViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public TaskViewModel setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public ClassificationEnum getClassificationEnum() {
        return classificationEnum;
    }

    public TaskViewModel setClassificationEnum(ClassificationEnum classificationEnum) {
        this.classificationEnum = classificationEnum;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskViewModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ProgressEnum getProgress() {
        return progress;
    }

    public TaskViewModel setProgress(ProgressEnum progress) {
        this.progress = progress;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TaskViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
