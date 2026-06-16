package com.example.demo.Repository;

import com.example.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    /// Telusko

    Users findByUsername(String username);


    //Optional<Users> findByEmail(String email);
    Optional<Users> findByResetPasswordToken(String token);


}
