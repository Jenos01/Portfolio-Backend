package com.example.demo.Entity;


import com.example.demo.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    //may add another field for the signed users wich is about who are they students,
    // recruters or experienced developpers, and the entry will be get from the field
    // in signup form which is ticket class and with it i can add another visual in power bi
    // which show me (could be a bar chart) wich will give me 3 bars each represent they class
    // of the user

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled = true;

    //******
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate modifiedAt;
}
