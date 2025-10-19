package com.example.demo.Controller;


import com.example.demo.Entity.Users;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Users addUser(@RequestBody Users user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }
}
