package com.dorsetsoftware.store.task;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<TaskDto>> getTasksByStatusAndUser(
            @RequestParam Integer statusId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        User user = userRepository.findByUsername(userDetails.getUsername());

        List<TaskDto> tasks = taskService.getTasksByStatusAndUser(status, user);

        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public void addNewTask(@Valid @RequestBody TaskCreateDto task, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        taskService.addNewTask(task, user);
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
