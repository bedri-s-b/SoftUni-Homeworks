package com.example.exam.service;

import com.example.exam.model.entity.ArtistEntity;
import com.example.exam.model.entity.enums.NameSingerEnum;

public interface ArtistService {
    void initArtists();


    ArtistEntity findByArtist(NameSingerEnum name);
}
