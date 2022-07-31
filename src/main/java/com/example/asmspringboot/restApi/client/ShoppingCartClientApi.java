package com.example.asmspringboot.restApi.client;

import com.example.asmspringboot.entity.dto.ShoppingCartDto;
import com.example.asmspringboot.service.ShoppingCartService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/shopping-carts")
@RequiredArgsConstructor
public class ShoppingCartClientApi {
    final ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<?> saveCart(
            @Valid @RequestBody ShoppingCartDto shoppingCartDto
    ) {
        return ResponseEntity.ok(shoppingCartService.save(shoppingCartDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartDetails(
            @PathVariable long userId
    ) {
        return ResponseEntity.ok(1);
    }
}
