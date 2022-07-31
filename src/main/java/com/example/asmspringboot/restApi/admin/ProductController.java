package com.example.asmspringboot.restApi.admin;

import com.example.asmspringboot.entity.Product;
import com.example.asmspringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/admin/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit,
                                     @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(productService.findAll(page, limit, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createNew(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    public ResponseEntity<?> delete(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok(true);
    }
}
