package com.dorsetsoftware.store.task;

import java.time.LocalDate;

import com.dorsetsoftware.store.status.StatusDto;

public class TaskDto {
    private Integer Id;
    private String title;
    private String description;
    private LocalDate doBy;
    private StatusDto status;

    public TaskDto() {
    }

    public TaskDto(Integer Id, String title, String description, LocalDate doBy, StatusDto status) {
        this.Id = Id;
        this.title = title;
        this.description = description;
        this.doBy = doBy;
        this.status = status;
    }

    // Getters and setters
    public Integer getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDoBy() {
        return doBy;
    }

    public void setDoBy(LocalDate doBy) {
        this.doBy = doBy;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
