package com.dorsetsoftware.store.task;

import java.time.LocalDate;
import java.util.List;

import com.dorsetsoftware.store.category.CategoryDto;
import com.dorsetsoftware.store.comment.CommentDto;
import com.dorsetsoftware.store.status.StatusDto;
import com.dorsetsoftware.store.user.UserDto;

public class TaskDto {
    private Integer Id;
    private String title;
    private String description;
    private LocalDate doBy;
    private Integer taskIndex;
    private StatusDto status;
    private UserDto user;
    private List<CategoryDto> categories;
    private List<CommentDto> comments;

    public TaskDto() {
    }

    public TaskDto(
        Integer Id,
        String title,
        String description,
        LocalDate doBy,
        Integer taskIndex,
        StatusDto status,
        UserDto user,
        List<CategoryDto> categories,
        List<CommentDto> comments) {
        this.Id = Id;
        this.title = title;
        this.description = description;
        this.doBy = doBy;
        this.taskIndex = taskIndex;
        this.status = status;
        this.user = user;
        this.categories = categories;
        this.comments = comments;
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

    public Integer getTaskIndex() {
        return taskIndex;
    }

    public void setTaskIndex(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public UserDto getUser() {
        return user;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
