package com.example.demo.Service;

import com.example.demo.Entity.Comment;
import com.example.demo.Entity.Rate;
import com.example.demo.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment modifyComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
