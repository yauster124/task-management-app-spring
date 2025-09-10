package com.dorsetsoftware.store.category;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorsetsoftware.store.user.User;
import com.dorsetsoftware.store.user.UserRepository;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    private UserRepository userRepository;

    public CategoryController(CategoryService categoryService, UserRepository userRepository) {
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategoriesByUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<CategoryDto> categories = categoryService.getCategoriesByUser(user);

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public CategoryDto addNewTask(@Valid @RequestBody CategoryCreateDto categoryCreateDto, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        return categoryService.addNewCategory(categoryCreateDto, user);
    }
}
