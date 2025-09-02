package com.dorsetsoftware.store.task;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks") 
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public void addNewTask(@Valid @RequestBody TaskCreateDto task) {
        taskService.addNewTask(task);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(
        @PathVariable Integer id,
        @Valid @RequestBody TaskUpdateDto updateDto) {
        return taskService.updateTask(id, updateDto);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTask(@PathVariable Integer id) {
        return taskService.deleteTask(id);
    }
}
