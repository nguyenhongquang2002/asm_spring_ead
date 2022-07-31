package com.example.asmspringboot.seeder;

import com.example.asmspringboot.entity.dto.UserRegisterDto;
import com.example.asmspringboot.entity.entityEnum.UserRole;
import com.example.asmspringboot.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeeder {
    final UserService userService;

    public void generate() {
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .fullName("quang")
                .email("quang@gmail.com")
                .phone("0966186860")
                .username("quang")
                .address("Hanoi")
                .role(UserRole.ADMIN)
                .password("123456")
                .build();
        userService.register(userRegisterDto);
    }
}
