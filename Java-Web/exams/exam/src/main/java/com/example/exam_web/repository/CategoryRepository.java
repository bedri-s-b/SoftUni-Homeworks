package com.example.exam_web.repository;

import com.example.exam_web.model.entity.CategoryEntity;
import com.example.exam_web.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    CategoryEntity findByName(CategoryEnum name);
}
