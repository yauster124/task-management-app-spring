package com.dorsetsoftware.store.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dorsetsoftware.store.user.User;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = new CategoryMapper();
    }

     public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
            .stream()
            .map(categoryMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<CategoryDto> getCategoriesByUser(User user) {
        return categoryRepository
            .findByUser(user)
            .stream()
            .map(categoryMapper::toDto)
            .collect(Collectors.toList());
    }

    public CategoryDto addNewCategory(CategoryCreateDto categoryCreateDto, User user) {
        Category category = new Category(
            categoryCreateDto.getTitle(),
            user
        );
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }
}
