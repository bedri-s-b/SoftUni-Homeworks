package com.example.exam_web.model.binding;

import com.example.exam_web.model.entity.enums.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ShipAddBindingModel {

    private String name;
    private Integer power;
    private Integer health;
    private LocalDate created;
    private CategoryEnum category;

    @NotNull
    @Size(min = 2,max = 10)
    public String getName() {
        return name;
    }

    public ShipAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Positive
    public Integer getPower() {
        return power;
    }

    public ShipAddBindingModel setPower(Integer power) {
        this.power = power;
        return this;
    }

    @NotNull
    @Positive
    public Integer getHealth() {
        return health;
    }

    public ShipAddBindingModel setHealth(Integer health) {
        this.health = health;
        return this;
    }

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getCreated() {
        return created;
    }

    public ShipAddBindingModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    @NotNull
    public CategoryEnum getCategory() {
        return category;
    }

    public ShipAddBindingModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
