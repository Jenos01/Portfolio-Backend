package com.example.demo.Service;

import com.example.demo.Entity.*;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.CommentRequest;
import com.example.demo.dto.CommentResponse;
import com.example.demo.dto.ProjectDto;
import com.example.demo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public CommentResponse addComment(CommentRequest commentRequest, Authentication authentication) {

       // UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        // Users author = userRepository.findById(principal.getId()).orElseThrow();
        String username = (String) authentication.getPrincipal();
        Users author = userRepository.findByUsername(username);


        Project project = projectRepository.findById(commentRequest.getProjectId()).orElseThrow();

        Comment comment = new Comment();
        comment.setComment((commentRequest.getCommentText()));
        comment.setUser(author);
        comment.setProject(project);

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponse(
                savedComment.getComment(),
               // savedComment.getId(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt(),
                new UserDto(
                       // author.getId(),
                        author.getUsername()
                        ),
                new ProjectDto(
                        project.getId(),
                        project.getName()
                )
        );
    }

    public Comment modifyComment(Comment comment) {

        return commentRepository.save(comment);
    }

//    public List<Comment> getAllComments() {
//        return commentRepository.findAll();
//    }

    public List<Comment> getProjectComments(String slug) {
//        Project project = projectRepository.findById(projectId);
        return commentRepository.findAllByProject_Slug(slug);
    }
}
