package com.dorsetsoftware.store.category;

import jakarta.validation.constraints.NotBlank;

public class CategoryCreateDto {
    @NotBlank(message = "Title is required")
    private String title;

    public CategoryCreateDto() {
    }

    public CategoryCreateDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
