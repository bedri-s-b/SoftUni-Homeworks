package com.example.exam12august2020.model.binding;

import com.example.exam12august2020.model.entity.ClassificationEntity;
import com.example.exam12august2020.model.entity.UserEntity;
import com.example.exam12august2020.model.entity.enums.ClassificationEnum;
import com.example.exam12august2020.model.entity.enums.ProgressEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddBindingModel {

    private String name;
    private String description;
    private ProgressEnum progress;
    private LocalDate dueDate;
    private ClassificationEnum classification;

    @NotNull
    @Size(min = 3,max = 20)
    public String getName() {
        return name;
    }

    public TaskAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }


    @NotNull
    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public TaskAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProgressEnum getProgress() {
        return progress;
    }

    public TaskAddBindingModel setProgress(ProgressEnum progress) {
        this.progress = progress;
        return this;
    }

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskAddBindingModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    @NotNull
    public ClassificationEnum getClassification() {
        return classification;
    }

    public TaskAddBindingModel setClassification(ClassificationEnum classification) {
        this.classification = classification;
        return this;
    }
}
