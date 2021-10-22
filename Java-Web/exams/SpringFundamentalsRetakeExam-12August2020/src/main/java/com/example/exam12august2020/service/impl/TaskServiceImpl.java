package com.example.exam12august2020.service.impl;

import com.example.exam12august2020.model.entity.ClassificationEntity;
import com.example.exam12august2020.model.entity.TaskEntity;
import com.example.exam12august2020.model.entity.UserEntity;
import com.example.exam12august2020.model.entity.enums.ProgressEnum;
import com.example.exam12august2020.model.service.TaskServiceModel;
import com.example.exam12august2020.model.view.TaskViewModel;
import com.example.exam12august2020.repository.TaskRepository;
import com.example.exam12august2020.sec.CurrentUser;
import com.example.exam12august2020.service.ClassificationService;
import com.example.exam12august2020.service.TaskService;
import com.example.exam12august2020.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ClassificationService classificationService;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, CurrentUser currentUser, UserService userService, ClassificationService classificationService) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.classificationService = classificationService;
    }

    @Override
    public void addTask(TaskServiceModel taskServiceModel) {
        TaskEntity task = modelMapper.map(taskServiceModel, TaskEntity.class);
        Long id = currentUser.getId();
        UserEntity user = userService.findById(id);
        ProgressEnum progress = ProgressEnum.OPEN;
        ClassificationEntity classification = classificationService.findByName(taskServiceModel.getClassification());
        task.setProgress(progress).setUser(user).setClassification(classification);
        taskRepository.save(task);
    }

    @Override
    public List<TaskViewModel> findAllTaskOrderDate() {
        return taskRepository.findAllByAndOrderByDate();
    }

    @Override
    public void changeProgress(Long id) {
        TaskEntity task = taskRepository.findById(id).orElse(null);
        ProgressEnum progress = task.getProgress();
        ProgressEnum newProgress = null;
        switch (progress) {
            case OPEN -> newProgress = ProgressEnum.IN_PROGRESS;
            case IN_PROGRESS -> newProgress = ProgressEnum.COMPLETED;
            case COMPLETED -> taskRepository.deleteById(id);
        }
        if (!progress.name().equals("COMPLETED")){
            taskRepository.changeProgressStatus(id,newProgress);
        }
    }
}
