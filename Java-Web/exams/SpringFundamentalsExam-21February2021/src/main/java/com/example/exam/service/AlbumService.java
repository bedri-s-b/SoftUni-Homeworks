package com.example.exam.service;

import com.example.exam.model.binding.AlbumAddBindingModel;
import com.example.exam.model.service.AlbumServiceModel;
import com.example.exam.model.view.AlbumViewModel;

import java.util.List;

public interface AlbumService {

    void addAlbum(AlbumServiceModel albumServiceModel);

    List<AlbumViewModel> findAllAlbums();

    void deleteAlbum(Long id);
}
