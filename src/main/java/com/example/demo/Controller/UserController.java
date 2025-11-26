package com.example.demo.Controller;


import com.example.demo.Entity.Users;
import com.example.demo.Service.UserService;
import com.example.demo.dto.LoginRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping // ("/sign up")  (create user)
    public Users addUser(@RequestBody Users user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Users user = userService.findByEmail(request.getEmail());

        if (user != null && user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }
    }


    /// Telusko
    @PostMapping("register")
    public Users register(@RequestBody Users user) {
    return userService.register(user);
    }

    @PostMapping("/signin") //equivalent l login fl video
    public String signin(@RequestBody Users user) {
        return userService.verify(user);
    }
}