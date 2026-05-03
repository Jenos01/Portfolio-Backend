package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
//import java.util.Date;
import java.time.LocalDate;
import com.example.demo.utils.SlugUtils;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private String provider;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String imagePath;

    private String slug;

    @PrePersist
    @PreUpdate
    public void generateSlug() {
        this.slug = SlugUtils.slugify(this.title);
    }

    public Certification(Long id, String title, String provider,
                         LocalDate date, String imagePath) {
        this.id = id;
        this.title = title;
        this.provider = provider;
        this.date = date;
        this.imagePath = imagePath;
    }
}
