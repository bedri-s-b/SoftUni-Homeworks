package com.example.exam_web.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class ShipEntity extends BaseEntity{

    private String name;
    private Integer health;
    private Integer power;
    private LocalDate created;
    private CategoryEntity category;
    private UserEntity user;

    public ShipEntity() {
    }

    @Column(nullable = false,unique = true)
    public String getName() {
        return name;
    }

    public ShipEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public Integer getHealth() {
        return health;
    }

    public ShipEntity setHealth(Integer health) {
        this.health = health;
        return this;
    }

    @Column(nullable = false)
    public Integer getPower() {
        return power;
    }

    public ShipEntity setPower(Integer power) {
        this.power = power;
        return this;
    }

    @Column(nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public ShipEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    public ShipEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public ShipEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
