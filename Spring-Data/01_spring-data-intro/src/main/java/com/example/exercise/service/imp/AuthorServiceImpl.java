package com.example.exercise.service.imp;

import com.example.exercise.model.entity.Author;
import com.example.exercise.repository.AuthorRepository;
import com.example.exercise.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    public static final String AUTHOR_PATH_FILE = "src/main/resources/authors.txt";

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }
        Files.readAllLines(Path.of(AUTHOR_PATH_FILE)).
                forEach(a -> {
                    String[] fullName = a.split("\\s+");
                    Author author = new Author(fullName[0], fullName[1]);
                    authorRepository.save(author);
                });

    }

    @Override
    public Author getRandomAuthor() {
        int randomId = ThreadLocalRandom.current().nextInt(1, (int) (authorRepository.count() + 1));

        return authorRepository.findById(randomId).orElse(null);
    }
}
