package com.dorsetsoftware.store.task;

import com.dorsetsoftware.store.category.CategoryMapper;
import com.dorsetsoftware.store.comment.CommentMapper;
import com.dorsetsoftware.store.status.StatusMapper;
import com.dorsetsoftware.store.user.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskMapper {
    private final StatusMapper statusMapper;
    private final CategoryMapper categoryMapper;
    private final CommentMapper commentMapper;

    public TaskMapper() {
        this.statusMapper = new StatusMapper();
        this.categoryMapper = new CategoryMapper();
        this.commentMapper = new CommentMapper();
    }

    public TaskDto toDto(Task task) {
        if (task == null)
            return null;

        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDoBy(),
                task.getTaskIndex(),
                statusMapper.toDto(task.getStatus()),
                task.getCategories()
                    .stream()
                    .map(categoryMapper::toDto)
                    .collect(Collectors.toList()),
                task.getComments()
                    .stream()
                    .map(commentMapper::toDto)
                    .collect(Collectors.toList())
        );
    }

    public Task toEntity(TaskCreateDto dto, User user) {
        if (dto == null)
            return null;

        return new Task(
                dto.getTitle(),
                dto.getDescription(),
                dto.getDoBy(),
                statusMapper.toEntity(dto.getStatus()),
                new ArrayList<>(),
                new ArrayList<>(),
                user);
    }
}
