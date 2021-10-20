package com.example.exam.init;

import com.example.exam.service.ArtistService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DateBaseInit implements CommandLineRunner {
    private final ArtistService artistService;

    public DateBaseInit(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public void run(String... args) throws Exception {
        artistService.initArtists();
    }
}
