package com.dorsetsoftware.store.task;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

import com.dorsetsoftware.store.status.StatusDto;

public class TaskUpdateDto {
    private String title;

    private String description;

    @FutureOrPresent(message = "Do by date must not be in the past")
    private LocalDate doBy;

    private StatusDto status;

    public TaskUpdateDto() {
    }

    public TaskUpdateDto(String title, String description, LocalDate doBy, StatusDto status) {
        this.title = title;
        this.description = description;
        this.doBy = doBy;
        this.status = status;
    }

    // Getters and setters
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
