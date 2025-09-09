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

    public TaskDto addNewTask(TaskCreateDto task, User user) {
        Status status = statusRepository.findById(task.getStatus().getId())
                .orElseThrow(() -> new RuntimeException("Status not found"));

        Integer maxIndex = taskRepository.findMaxTaskIndexByStatus(task.getStatus().getId());
        int nextIndex = (maxIndex == null ? 0 : maxIndex + 1);

        Task newTask = new Task(
                task.getTitle(),
                task.getDescription(),
                task.getDoBy(),
                nextIndex,
                status,
                user);

        taskRepository.save(newTask);

        return taskMapper.toDto(newTask);
    }

    @Transactional
    public TaskDto updateTask(Integer id, TaskUpdateDto dto) {
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
                Integer maxIndex = taskRepository.findMaxTaskIndexByStatus(dto.getStatus().getId());
                if (maxIndex != null) {
                    taskRepository.incrementIndicesFrom(status, maxIndex + 1, dto.getTaskIndex());
                }

                maxIndex = taskRepository.findMaxTaskIndexByStatus(existingTask.getStatus().getId());
                if (maxIndex != null) {
                    taskRepository.decrementIndicesFrom(existingTask.getStatus(), existingTask.getTaskIndex(),
                            maxIndex + 1);
                }
            }

            if (dto.getStatus().getId() == existingTask.getStatus().getId()) {
                if (dto.getTaskIndex() > existingTask.getTaskIndex()) {
                    taskRepository.decrementIndicesFrom(status, existingTask.getTaskIndex(), dto.getTaskIndex());
                } else {
                    taskRepository.incrementIndicesFrom(status, existingTask.getTaskIndex(), dto.getTaskIndex());
                }
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

    @Transactional
    public Boolean deleteTask(Integer id) {
        if (taskRepository.existsById(id)) {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Task not found"));
            Integer maxIndex = taskRepository.findMaxTaskIndexByStatus(task.getStatus().getId());
            if (maxIndex != null) {
                taskRepository.decrementIndicesFrom(task.getStatus(), task.getTaskIndex(),
                        maxIndex + 1);
            }
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
