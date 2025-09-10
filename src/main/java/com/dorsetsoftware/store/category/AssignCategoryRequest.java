package com.dorsetsoftware.store.category;

public class AssignCategoryRequest {
    public Integer id;
    public String title;

    public AssignCategoryRequest(){}

    public AssignCategoryRequest(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
