package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    private String githubRepoLink;
    @Column(nullable = false)
    private String imagePath;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "project_id") // creates the foreign key in ProjectSkills
    private List<ProjectSkills> projectSkills = new ArrayList<>();







//    public void addProjectSkills(Skills skills) {
//        ProjectSkills associaton = new ProjectSkills();
//        associaton.setSkills(skills);
//        associaton.setProject(this);
//
//        this.projectSkills.add(associaton);
//    }

}
