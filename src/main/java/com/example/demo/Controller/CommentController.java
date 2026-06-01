package com.example.demo.Controller;
import com.example.demo.Entity.Comment;
import com.example.demo.Entity.Rate;
import com.example.demo.Service.CommentService;
import com.example.demo.dto.CommentRequest;
import com.example.demo.dto.CommentResponse;
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

//    @GetMapping
//    public List<Comment> getAllComments(){
//        return commentService.getAllComments();
//    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("add-comment")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest commentRequest, Authentication authentication){
        System.out.println("CONTROLLER HIT");
        CommentResponse comment = commentService.addComment(commentRequest, authentication);
        return ResponseEntity.ok(comment);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("modify")
    public Comment modifyComment(Comment comment) {
        return commentService.modifyComment(comment);
    }

//    @GetMapping
//    public List<Comment> getProjectComments(@PathVariable Long projectId){
//        return commentService.getProjectComments(projectId);
//    }

    @GetMapping("project/{slug}")
    public List<Comment> getProjectComments(@PathVariable String slug){
        return commentService.getProjectComments(slug);
    }

}
