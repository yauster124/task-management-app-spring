package com.dorsetsoftware.store.task;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dorsetsoftware.store.status.*;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;
    private final TaskMapper taskMapper;
    private final StatusMapper statusMapper;

    public TaskService(TaskRepository taskRepository, StatusRepository statusRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.taskMapper = new TaskMapper();
        this.statusMapper = new StatusMapper();
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addNewTask(TaskCreateDto task) {
        Status status = statusRepository.findById(task.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("Status not found"));

        taskRepository.save(new Task(
                task.getTitle(),
                task.getDescription(),
                task.getDoBy(),
                status));
    }

    public TaskDto updateTask(Integer id, TaskUpdateDto dto) {
        Task existingTask = taskRepository.findById(id).orElseThrow();

        if (dto.getTitle() != null) {
            existingTask.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            existingTask.setDescription(dto.getDescription());
        }
        if (dto.getDoBy() != null) {
            existingTask.setDoBy(dto.getDoBy());;
        }
        if (dto.getStatus() != null) {
            existingTask.setStatus(statusMapper.toEntity(dto.getStatus()));
        }

        Task saved = taskRepository.save(existingTask);
        return taskMapper.toDto(saved);
    }

    public Boolean deleteTask(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
