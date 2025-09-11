package com.dorsetsoftware.store.comment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dorsetsoftware.store.user.User;
import com.dorsetsoftware.store.user.UserRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;

    public CommentController(CommentService commentService, UserRepository userRepository) {
        this.commentService = commentService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{id}")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id, @RequestBody CommentCreateDto dto, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        CommentDto newComment = commentService.addComment(id, dto, user);

         return ResponseEntity.ok(newComment);
    }
}
