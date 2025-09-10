package com.dorsetsoftware.store.category;

public class UnassignCategoryRequest {
    public Integer id;

    public UnassignCategoryRequest() {
    }

    public UnassignCategoryRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
}
