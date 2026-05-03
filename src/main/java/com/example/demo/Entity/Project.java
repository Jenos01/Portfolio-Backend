package com.example.demo.Entity;

import com.example.demo.utils.SlugUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    private String slug;

    @PrePersist
    @PreUpdate
    public void generateSlug() {
        this.slug = SlugUtils.slugify(this.name);
    }

    public Project(Long id, String name, String description,
                         String githubRepoLink, String imagePath, List<ProjectSkills> projectSkills) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.githubRepoLink = githubRepoLink;
        this.imagePath = imagePath;
        this.projectSkills = projectSkills;
    }

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
