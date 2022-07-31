package com.example.asmspringboot.restApi.admin;


import com.example.asmspringboot.entity.Category;
import com.example.asmspringboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/admin/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit,
                                     @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(categoryService.findAll(page, limit, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createNew(@Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }

    public ResponseEntity<?> delete(@PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(true);
    }
}
