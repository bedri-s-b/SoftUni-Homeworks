package com.example.exercise.service.imp;

import com.example.exercise.model.entity.*;
import com.example.exercise.repository.BookRepository;
import com.example.exercise.service.AuthorService;
import com.example.exercise.service.BookService;
import com.example.exercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_FILE_PATH = "src/main/resources/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;


    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(BOOK_FILE_PATH)).forEach(boo ->{
            String[] allInfo = boo.split("\\s+");
            Book book = createBookFromInfo(allInfo);
            bookRepository.save(book);
        });
    }

    @Override
    public List<String> findAllBookAfterYear(int year) {
        return bookRepository.findAllByReleaseDateAfter(LocalDate.of(year,12,31))
                .stream().map(b -> String.format("%s %s",b.getTitle(),b.getReleaseDate()))
                .distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookWithReleaseDateBeforeYear(int year) {
        return bookRepository.findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream().map(b -> String.format("%s %s %s",b.getAuthor().getFirstName(),b.getAuthor().getLastName(),b.getReleaseDate()))
                .distinct().collect(Collectors.toList());
    }

    private Book createBookFromInfo(String[] allInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(allInfo[0])];
        LocalDate releaseDate = LocalDate.parse(allInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int copies = Integer.parseInt(allInfo[2]);
        BigDecimal price = new BigDecimal(allInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(allInfo[4])];
        String title = Arrays.stream(allInfo).skip(5L).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();

        Set<Category> categories = categoryService.getRandomCategories();
        return new Book(editionType,releaseDate,copies,price,ageRestriction,title,author,categories);

    }
}
