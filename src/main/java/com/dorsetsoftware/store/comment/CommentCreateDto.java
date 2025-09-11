package com.dorsetsoftware.store.comment;

import jakarta.validation.constraints.NotBlank;

public class CommentCreateDto {
    @NotBlank(message = "Comment can't be blank")
    private String content;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
