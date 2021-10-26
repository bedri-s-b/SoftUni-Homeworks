package com.example.exam_web.repository;

import com.example.exam_web.model.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    @Query("SELECT s FROM ShipEntity s WHERE s.user.id = :id ORDER BY s.name ,s.health, s.power")
    List<ShipEntity> findAllById(@Param(value = "id") Long id);

    List<ShipEntity> findAll();

    @Transactional
    @Modifying
    @Query("UPDATE ShipEntity s SET s.health = :afterAttack WHERE s.id = :id")
    void reduceHealth(@Param(value = "afterAttack") int afterAttack
            , @Param(value = "id") Long id);
}
