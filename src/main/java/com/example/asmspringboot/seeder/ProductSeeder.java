package com.example.asmspringboot.seeder;

import com.github.javafaker.Faker;
import com.example.asmspringboot.entity.Product;
import com.example.asmspringboot.entity.entityEnum.ProductStatus;
import com.example.asmspringboot.repository.ProductRepository;
import com.example.asmspringboot.util.NumberHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSeeder {
    private static final int NUMBER_OF_PRODUCTS = 100;
    public static List<Product> productList = new ArrayList<>();
    final ProductRepository productRepository;

    public void generate() {
        Faker faker = new Faker();
        int maxIndexOfCategories = CategorySeeder.categoryList.size() - 1;
        int minIndexOfCategories = 0;
        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++
        ) {
            Product product = Product.builder()
                    .name(faker.name().name())
                    .quantity(NumberHelper.generateRandom(10, 1000))
                    .thumbnail("https://danchoioto.vn/wp-content/uploads/2019/04/bmw-i8-gia.jpg")
                    .details(faker.superhero().descriptor())
                    .description(faker.weather().description())
                    .unitPrice(NumberHelper.generateRandom(10000, 1000000))
                    .category(CategorySeeder.categoryList.get(NumberHelper.generateRandom(minIndexOfCategories, maxIndexOfCategories)))
                    .status(ProductStatus.ACTIVE)
                    .build();
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }
}
