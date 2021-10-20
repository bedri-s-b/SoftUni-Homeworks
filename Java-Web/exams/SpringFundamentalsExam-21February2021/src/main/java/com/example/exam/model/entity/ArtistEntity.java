package com.example.exam.model.entity;

import com.example.exam.model.entity.enums.NameSingerEnum;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    private NameSingerEnum name;
    private String careerInformation;

    public ArtistEntity() {
    }

    @Enumerated(EnumType.STRING)
    public NameSingerEnum getName() {
        return name;
    }

    public ArtistEntity setName(NameSingerEnum name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getCareerInformation() {
        return careerInformation;
    }

    public ArtistEntity setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
