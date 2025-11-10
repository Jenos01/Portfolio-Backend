package com.example.demo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String logoImage;


    ///  7al mo2a9et just 5ater l cascade wel orphanRemoval (li mouch aajebni ennou hakka twalli bidirectional)
    @OneToMany(mappedBy = "skills", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProjectSkills> projectSkills = new ArrayList<>();

}
