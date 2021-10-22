package com.example.exam12august2020.service;

import com.example.exam12august2020.model.service.TaskServiceModel;
import com.example.exam12august2020.model.view.TaskViewModel;

import java.util.List;

public interface TaskService {
    void addTask(TaskServiceModel taskServiceModel);

    List<TaskViewModel> findAllTaskOrderDate();

    void changeProgress(Long id);
}
