package com.example.exercise.service.imp;

import com.example.exercise.model.entity.Category;
import com.example.exercise.repository.CategoryRepository;
import com.example.exercise.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_PATH = "src/main/resources/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0){
            return;
        }
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH)).stream()
                .filter(r -> !r.isEmpty())
               .forEach(r ->{
                   Category category = new Category(r);
                   categoryRepository.save(category);
               });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();

        int repoCount = (int) categoryRepository.count();

        int rantInt = ThreadLocalRandom.current().nextInt(1,4);
        for (int i = 0; i < rantInt; i++) {
            int randomId = ThreadLocalRandom.current().nextInt(1, repoCount+ 1);
            Category category = categoryRepository.findById(randomId).orElse(null);
            categories.add(category);
        }
        return categories;
    }
}
