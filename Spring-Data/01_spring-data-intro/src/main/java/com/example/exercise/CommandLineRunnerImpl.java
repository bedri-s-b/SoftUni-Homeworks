package com.example.exercise;

import com.example.exercise.model.entity.Book;
import com.example.exercise.service.AuthorService;
import com.example.exercise.service.BookService;
import com.example.exercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;


    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("Choice number of Exercise:");

        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int numberExercise = Integer.parseInt(bufferedReader.readLine());

        switch (numberExercise){
            case 1:
                printAllBooksAfterYear(2000);
                break;
            case 2:
                printAllAuthorsWithBooksReleaseDateBeforeYear(1990);
        }

    }

    private void printAllAuthorsWithBooksReleaseDateBeforeYear(int year) {
        bookService.findAllBookWithReleaseDateBeforeYear(year).forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
       bookService.findAllBookAfterYear(year).forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedCategories();
        bookService.seedBooks();
    }
}
