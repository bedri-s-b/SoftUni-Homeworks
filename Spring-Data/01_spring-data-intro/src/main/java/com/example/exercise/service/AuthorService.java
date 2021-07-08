package com.example.exercise.service;

import com.example.exercise.model.entity.Author;

import java.io.IOException;

public interface AuthorService {
    void seedCategories() throws IOException;

    Author getRandomAuthor();
}
