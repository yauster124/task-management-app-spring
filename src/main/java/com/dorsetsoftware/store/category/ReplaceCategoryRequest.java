package com.dorsetsoftware.store.category;

public class ReplaceCategoryRequest {
    public Integer oldId;
    public Integer newId;
    public String title;

    public ReplaceCategoryRequest(){}

    public ReplaceCategoryRequest(Integer oldId, Integer newId, String title) {
        this.oldId = oldId;
        this.newId = newId;
        this.title = title;
    }

    public Integer getOldId() {
        return this.oldId;
    }

    public Integer getNewId() {
        return this.newId;
    }

    public String getTitle() {
        return this.title;
    }
}
