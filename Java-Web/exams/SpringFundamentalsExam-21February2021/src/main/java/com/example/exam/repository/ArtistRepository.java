package com.example.exam.repository;

import com.example.exam.model.entity.ArtistEntity;
import com.example.exam.model.entity.enums.NameSingerEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity,Long> {
    ArtistEntity findByName(NameSingerEnum name);
}
