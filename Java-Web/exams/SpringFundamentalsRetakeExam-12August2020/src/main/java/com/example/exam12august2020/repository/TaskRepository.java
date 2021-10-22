package com.example.exam12august2020.repository;

import com.example.exam12august2020.model.entity.TaskEntity;
import com.example.exam12august2020.model.entity.enums.ProgressEnum;
import com.example.exam12august2020.model.view.TaskViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<TaskEntity ,Long> {

    @Query("SELECT new com.example.exam12august2020.model.view.TaskViewModel(t.id,t.name,t.user.username,t.classification.classificationName,t.dueDate,t.progress) FROM TaskEntity t ORDER BY t.dueDate")
    List<TaskViewModel> findAllByAndOrderByDate();

    @Modifying()
    @Query("UPDATE TaskEntity t SET t.progress = :progress WHERE t.id = :id")
    void changeProgressStatus(@Param(value = "id") Long id,
                              @Param(value = "progress") ProgressEnum progress);
}
