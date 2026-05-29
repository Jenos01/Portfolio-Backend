package com.example.demo.Controller;
import com.example.demo.Entity.Comment;
import com.example.demo.Entity.Rate;
import com.example.demo.Service.CommentService;
import com.example.demo.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("add-comment")
    public ResponseEntity<Comment> addComment(@RequestBody CommentRequest commentRequest, Authentication authentication){
        Comment comment = commentService.addComment(commentRequest, authentication);
        return ResponseEntity.ok(comment);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("modify")
    public Comment modifyComment(Comment comment) {
        return commentService.modifyComment(comment);
    }

}
