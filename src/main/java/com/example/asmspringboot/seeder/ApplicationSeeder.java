package com.example.asmspringboot.seeder;

import com.example.asmspringboot.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationSeeder implements CommandLineRunner {
    final CategorySeeder categorySeeder;
    final ProductSeeder productSeeder;
    final OrderSeeder orderSeeder;
    final UserSeeder userSeeder;
    private static final boolean IS_SEED = false;

    @Override
    public void run(String... args) throws Exception {
        if(IS_SEED) {
            categorySeeder.generate();
            productSeeder.generate();
            orderSeeder.superGenerate();
            userSeeder.generate();
        }
    }
}
