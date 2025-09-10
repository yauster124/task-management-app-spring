package com.dorsetsoftware.store.category;

public class CategoryDto {
    private Integer id;
    private String title;

    public CategoryDto() {
    }

    public CategoryDto(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
