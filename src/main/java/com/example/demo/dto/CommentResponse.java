package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponse {
    String comment;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    UserDto user;
    ProjectDto project;
}
