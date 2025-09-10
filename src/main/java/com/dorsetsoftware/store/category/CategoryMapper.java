package com.dorsetsoftware.store.category;

import com.dorsetsoftware.store.user.User;

public class CategoryMapper {
    public CategoryDto toDto(Category status) {
        if (status == null)
            return null;

        return new CategoryDto(
                status.getId(),
                status.getTitle()
        );
    }

    public Category toEntity(CategoryDto dto, User user) {
        if (dto == null)
            return null;

        return new Category(
                dto.getTitle(),
                user
        );
    }
}
