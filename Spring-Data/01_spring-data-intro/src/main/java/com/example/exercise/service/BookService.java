package com.example.exercise.service;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;
    List<String> findAllBookAfterYear(int year);

    List<String> findAllBookWithReleaseDateBeforeYear(int year);
}
