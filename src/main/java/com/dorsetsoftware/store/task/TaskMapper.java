package com.dorsetsoftware.store.task;

import com.dorsetsoftware.store.status.StatusMapper;
import com.dorsetsoftware.store.user.User;

public class TaskMapper {
    private final StatusMapper statusMapper;

    public TaskMapper() {
        this.statusMapper = new StatusMapper();
    }

    public TaskDto toDto(Task task) {
        if (task == null) return null;

        return new TaskDto(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDoBy(),
            task.getTaskIndex(),
            statusMapper.toDto(task.getStatus())
        );
    }

    public Task toEntity(TaskCreateDto dto, User user) {
        if (dto == null) return null;
        
        return new Task(
            dto.getTitle(),
            dto.getDescription(),
            dto.getDoBy(),
            statusMapper.toEntity(dto.getStatus()),
            user
        );
    }
}
