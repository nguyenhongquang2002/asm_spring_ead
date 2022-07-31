package com.example.asmspringboot.seeder;

import com.github.javafaker.Faker;
import com.example.asmspringboot.entity.Category;
import com.example.asmspringboot.entity.entityEnum.CategoryStatus;
import com.example.asmspringboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorySeeder {
    private static final int NUMBER_OF_CATEGORIES = 20;
    public static List<Category> categoryList = new ArrayList<>();
    final CategoryRepository categoryRepository;

    public void generate() {
        Faker faker = new Faker();
        for (int i = 0; i < NUMBER_OF_CATEGORIES; i++
        ) {
            Category category = Category.builder()
                    .name(faker.name().name())
                    .status(CategoryStatus.ACTIVE)
                    .build();
            categoryList.add(category);
        }
        categoryRepository.saveAll(categoryList);
    }
}
