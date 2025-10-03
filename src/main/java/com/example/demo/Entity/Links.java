package com.example.demo.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String linkedinLink;

    private String githubLink;
}
