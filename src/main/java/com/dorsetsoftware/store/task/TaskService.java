package com.dorsetsoftware.store.task;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.dorsetsoftware.store.status.*;
import com.dorsetsoftware.store.user.User;

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

    public void addNewTask(TaskCreateDto task, User user) {
        Status status = statusRepository.findById(task.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("Status not found"));
        
        Integer maxIndex = taskRepository.findMaxTaskIndexByStatus(task.getStatus().getId());
        int nextIndex = (maxIndex == null ? 0 : maxIndex + 1);

        taskRepository.save(new Task(
                task.getTitle(),
                task.getDescription(),
                task.getDoBy(),
                nextIndex,
                status,
                user));
    }

    @Transactional
    public TaskDto updateTask(Integer id, TaskUpdateDto dto) {
        System.out.println(dto.getTitle());
        System.out.println(dto.getDescription());
        System.out.println(dto.getTaskIndex());
        System.out.println(dto.getStatus().getTitle());
        Task existingTask = taskRepository.findById(id).orElseThrow();

        if (dto.getTitle() != null) {
            existingTask.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            existingTask.setDescription(dto.getDescription());
        }
        if (dto.getDoBy() != null) {
            existingTask.setDoBy(dto.getDoBy());
        }

        if (dto.getTaskIndex() != null) {
            Status status = statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("Status not found"));

            if (dto.getStatus().getId() != existingTask.getStatus().getId()) {
                taskRepository.incrementIndicesFrom(status, dto.getTaskIndex());
            }

            if (dto.getStatus().getId() == existingTask.getStatus().getId()) {
                taskRepository.decrementIndicesFrom(status, existingTask.getTaskIndex(), dto.getTaskIndex());
            }

            existingTask.setTaskIndex(dto.getTaskIndex());
        }

        if (dto.getStatus() != null) {
            Status status = statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("Status not found"));
            existingTask.setStatus(status);
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

    public List<TaskDto> getTasksByStatusAndUser(Status status, User user) {
        return taskRepository.findByStatusAndUserOrderByTaskIndexAsc(status, user).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }
}
