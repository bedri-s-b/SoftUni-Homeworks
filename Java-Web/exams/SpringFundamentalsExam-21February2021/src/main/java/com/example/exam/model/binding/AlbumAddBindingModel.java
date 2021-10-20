package com.example.exam.model.binding;

import com.example.exam.model.entity.enums.GenreEnum;
import com.example.exam.model.entity.enums.NameSingerEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddBindingModel {
    private String name;
    private String imageUrl;
    private BigDecimal price;
    private Integer copies;
    private LocalDate releasedDate;
    private String producer;
    private NameSingerEnum artist;
    private GenreEnum genre;
    private String description;

    @NotNull
    @Size(min = 3,max = 20)
    public String getName() {
        return name;
    }

    public AlbumAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Size(min = 5)
    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @NotNull
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public AlbumAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotNull
    @Positive
    public Integer getCopies() {
        return copies;
    }

    public AlbumAddBindingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public AlbumAddBindingModel setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AlbumAddBindingModel setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public NameSingerEnum getArtist() {
        return artist;
    }

    public AlbumAddBindingModel setArtist(NameSingerEnum artist) {
        this.artist = artist;
        return this;
    }

    @NotNull
    public GenreEnum getGenre() {
        return genre;
    }

    public AlbumAddBindingModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    @NotNull
    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public AlbumAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
