package com.dorsetsoftware.store.comment;

import com.dorsetsoftware.store.task.TaskDto;
import com.dorsetsoftware.store.user.UserDto;

import java.time.LocalDateTime;

public class CommentDto {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private UserDto user;

    public CommentDto() {
    }

    public CommentDto(Integer id, String content, LocalDateTime createdAt, UserDto user) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto getUser() {
        return user;
    }
}
