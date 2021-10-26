package com.example.exam_web.service.impl;

import com.example.exam_web.model.entity.CategoryEntity;
import com.example.exam_web.model.entity.enums.CategoryEnum;
import com.example.exam_web.repository.CategoryRepository;
import com.example.exam_web.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategory() {
        if (categoryRepository.count() != 0){
            return;
        }
        CategoryEnum[] categories = CategoryEnum.values();

        Arrays.stream(categories)
                .forEach(categoryEnum -> {
                    CategoryEntity category = new CategoryEntity();
                   category.setName(categoryEnum).setDescription(categoryEnum.name());
                   categoryRepository.save(category);
                });
    }
}
