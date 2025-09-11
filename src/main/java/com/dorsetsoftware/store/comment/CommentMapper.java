package com.dorsetsoftware.store.comment;

import com.dorsetsoftware.store.user.UserMapper;

public class CommentMapper {
    private final UserMapper userMapper;

    public CommentMapper() {
        this.userMapper = new UserMapper();
    }

    public CommentDto toDto(Comment comment) {
        if (comment == null)
            return null;

        return new CommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                userMapper.toDto(comment.getUser())
        );
    }
}
