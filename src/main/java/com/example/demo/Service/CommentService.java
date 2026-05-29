package com.example.demo.Service;

import com.example.demo.Entity.*;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public Comment addComment(CommentRequest commentRequest, Authentication authentication) {

       // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        // Users author = userRepository.findById(principal.getId()).orElseThrow();
        String username = (String) authentication.getPrincipal();
        Users author = userRepository.findByUsername(username);


        Project project = projectRepository.findById(commentRequest.getProjectId()).orElseThrow();

        Comment comment = new Comment();
        comment.setComment((commentRequest.getCommentText()));
        comment.setUser(author);
        comment.setProject(project);

        return commentRepository.save(comment);
    }

    public Comment modifyComment(Comment comment) {

        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
