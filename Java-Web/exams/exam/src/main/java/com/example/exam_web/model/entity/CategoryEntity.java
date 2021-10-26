package com.example.exam_web.model.entity;

import com.example.exam_web.model.entity.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    private CategoryEnum name;
    private String description;

    public CategoryEntity() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public CategoryEnum getName() {
        return name;
    }


    public CategoryEntity setName(CategoryEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
