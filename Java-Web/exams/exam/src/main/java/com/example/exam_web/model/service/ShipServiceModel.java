package com.example.exam_web.model.service;

import com.example.exam_web.model.entity.CategoryEntity;
import com.example.exam_web.model.entity.UserEntity;
import com.example.exam_web.model.entity.enums.CategoryEnum;

import java.time.LocalDate;

public class ShipServiceModel {

    private Long id;
    private String name;
    private Integer health;
    private Integer power;
    private LocalDate created;
    private CategoryEnum category;
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public ShipServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public ShipServiceModel setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public ShipServiceModel setPower(Integer power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipServiceModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ShipServiceModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShipServiceModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
