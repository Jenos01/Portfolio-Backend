package com.example.demo.Controller;


import com.example.demo.Entity.Users;
import com.example.demo.Service.UserService;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RefreshTokenRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin("*")
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping // ("/sign up")  (create user)
//    public Users addUser(@RequestBody Users user) {
//        return userService.addUser(user);
//    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("users")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

///*********************************************
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//    //public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//        //Users user = userService.findByEmail(request.getEmail());
//        Users user = userService.findByUsername(request.getUsername());
//
//        if (user != null && user.getPassword().equals(request.getPassword())) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
//        }
//    }

@PostMapping("/login") //signin
///  Login should take a dedicated LoginRequest DTO, not the full Users entity
///  @RequestBody LoginRequest not @RequestBody Users user
/// but if change it i'll need to do some changes starting by the verify function
//public ResponseEntity<LoginResponse> login(@RequestBody Users user) {
//    try{
//         LoginResponse response = new LoginResponse();
//         //userService.verify(user);
//        String token = userService.verify(user);
//        response.setToken(token);
//        //return new ResponseEntity<>(response, HttpStatus.OK);
//        //shortand it
//        return ResponseEntity.ok(response);
//
//    } catch (Exception e) {
//        LoginResponse response = new LoginResponse();
//        response.setMessage("Invalid username or password");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }
//}
public ResponseEntity<LoginResponse> login(@RequestBody Users user) {
    try {

        LoginResponse response = userService.verify(user);

        return ResponseEntity.ok(response);

    } catch (Exception e) {

        LoginResponse response = new LoginResponse();

        response.setMessage("Invalid username or password");

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
}

///****************************************
@PostMapping("/refresh")
public ResponseEntity<LoginResponse> refreshToken(
        @RequestBody RefreshTokenRequest request
) {
    return ResponseEntity.ok(
            userService.refreshToken(request.getRefreshToken())
    );
}
///****************************************

    @PostMapping("register")
    public Users register(@RequestBody Users user) {
    return userService.register(user);
    }

//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("/signin")  //login
//    public String signin(@RequestBody Users user) {
//        return userService.verify(user);
//    }
}