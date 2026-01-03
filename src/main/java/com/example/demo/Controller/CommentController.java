package com.example.demo.Controller;
import com.example.demo.Entity.Comment;
import com.example.demo.Entity.Rate;
import com.example.demo.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Comment addComment(Comment comment){
        return commentService.addComment(comment);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public Comment modifyComment(Comment comment) {
        return commentService.modifyComment(comment);
    }

}
