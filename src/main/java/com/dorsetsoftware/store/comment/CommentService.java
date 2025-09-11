package com.dorsetsoftware.store.comment;

import com.dorsetsoftware.store.task.Task;
import com.dorsetsoftware.store.task.TaskRepository;
import com.dorsetsoftware.store.user.User;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.commentMapper = new CommentMapper();
    }

    public CommentDto addComment(Integer taskId, CommentCreateDto dto, User user) {
        Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("Task not found"));

        Comment newComment = new Comment(
            dto.getContent(),
            task,
            user
        );

        Comment saved = commentRepository.save(newComment);
        task.getComments().add(saved);
        taskRepository.save(task);

        return commentMapper.toDto(saved);
    }
}
