package com.example.exam_web.model.view;

import com.example.exam_web.model.entity.CategoryEntity;
import com.example.exam_web.model.entity.UserEntity;

import java.time.LocalDate;

public class ShipViewModel {

    private Long id;
    private String name;
    private Integer health;
    private Integer power;
    private LocalDate created;
    private CategoryEntity category;
    private UserEntity user;

    public String getName() {
        return name;
    }

    public ShipViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public ShipViewModel setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public ShipViewModel setPower(Integer power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipViewModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ShipViewModel setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShipViewModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ShipViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
