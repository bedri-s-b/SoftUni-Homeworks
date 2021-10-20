package com.example.exam.service.impl;

import com.example.exam.model.entity.AlbumEntity;
import com.example.exam.model.entity.ArtistEntity;
import com.example.exam.model.entity.UserEntity;
import com.example.exam.model.service.AlbumServiceModel;
import com.example.exam.model.view.AlbumViewModel;
import com.example.exam.repository.AlbumRepository;
import com.example.exam.sec.CurrentUser;
import com.example.exam.service.AlbumService;
import com.example.exam.service.ArtistService;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final ArtistService artistService;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.artistService = artistService;
    }


    @Override
    public void addAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity album = modelMapper.map(albumServiceModel, AlbumEntity.class);
        UserEntity user = userService.findById(currentUser.getId());
        ArtistEntity artist = artistService.findByArtist(albumServiceModel.getArtist().getName());
        album.setAddedFrom(user).setArtist(artist);
        albumRepository.save(album);
    }

    @Override
    public List<AlbumViewModel> findAllAlbums() {
        List<AlbumEntity> all = albumRepository.findAll();
        return all.stream().map(album -> {
            AlbumViewModel albumViewModel = modelMapper.map(album, AlbumViewModel.class);
            LocalDate releasedDate = album.getReleasedDate();
            albumViewModel.setReleaseDate(releasedDate);
            albumViewModel.setArtist(album.getArtist().getName().name());
            return albumViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
