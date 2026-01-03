package com.example.demo.Repository;

import com.example.demo.Entity.Comment;
import com.example.demo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Project project(Project project);
}
