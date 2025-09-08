package com.dorsetsoftware.store.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dorsetsoftware.store.status.Status;
import com.dorsetsoftware.store.status.StatusRepository;
import com.dorsetsoftware.store.user.User;
import com.dorsetsoftware.store.user.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    public TaskController(TaskService taskService, StatusRepository statusRepository,
            UserRepository userRepository) {
        this.taskService = taskService;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Map<Integer, List<TaskDto>>> getTasksByStatusAndUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        List<Status> statuses = statusRepository.findAll();

        Map<Integer, List<TaskDto>> tasksByStatus = new HashMap<>();
        for (Status status : statuses) {
            List<TaskDto> tasks = taskService.getTasksByStatusAndUser(status, user);
            tasksByStatus.put(status.getId(), tasks);
        }

        return ResponseEntity.ok(tasksByStatus);
    }

    @PostMapping
    public void addNewTask(@Valid @RequestBody TaskCreateDto task, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        taskService.addNewTask(task, user);
    }

    @PatchMapping("/{id}")
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
