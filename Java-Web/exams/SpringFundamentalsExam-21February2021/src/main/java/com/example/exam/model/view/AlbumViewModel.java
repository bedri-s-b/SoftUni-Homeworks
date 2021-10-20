package com.example.exam.model.view;


import com.example.exam.model.entity.ArtistEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumViewModel {

    private Long id;
    private String name;
    private String artist;
    private String genre;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Integer copies;


    public String getName() {
        return name;
    }

    public AlbumViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumViewModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public AlbumViewModel setGenre(String genre) {
        this.genre = genre;
        return this;
    }


    @DateTimeFormat
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumViewModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AlbumViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumViewModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
