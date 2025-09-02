package com.dorsetsoftware.store.status;

public class StatusDto {
    private Integer id;
    private String title;

    public StatusDto() {
    }

    public StatusDto(Integer id, String title) {
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
