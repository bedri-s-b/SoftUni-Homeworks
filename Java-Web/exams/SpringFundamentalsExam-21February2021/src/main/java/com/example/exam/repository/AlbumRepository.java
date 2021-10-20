package com.example.exam.repository;

import com.example.exam.model.entity.AlbumEntity;
import com.example.exam.model.view.AlbumViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity,Long> {

}
