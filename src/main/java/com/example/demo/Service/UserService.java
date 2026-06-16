package com.example.demo.Service;


import com.example.demo.Entity.Users;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.LoginResponse;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService; //*


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final AuthenticationManager authnManager ;

    private final JWTService jwtService;

//    public Users addUser(Users user) {
//        /// check existance first
//        user.setPassword(encoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users findByUsername(String username){
        return userRepository.findByUsername(username);
    }


    /// Telusko
    public Users register(Users user) { /// after registring the role of the user should be  USER

        Users existing = userRepository.findByEmail(user.getEmail());
        if (existing == null) {
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User with this email already exists");
        }
    }

//    public String verify(Users user) {
//        Authentication authentication =
//                authnManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        if (authentication.isAuthenticated()) {
//           // return jwtService.generateToken(user.getUsername());
//            return jwtService.generateToken(userDetails);
//        }
//        return "Fail";
//    }

    //*
//    public String verify(Users user) {
//
//        Authentication authentication =
//                authnManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                user.getUsername(), user.getPassword()
//                        )
//                );
//
//
//        /// this one is  commeneted and changed to the one below bcz If authentication fails, authnManager.authenticate() will throw an exception automatically so i dont need to handle it here and i made some changes about that in the controller (try catch block) and in the LoginResponse
////        if (authentication.isAuthenticated()) {
////            UserDetails userDetails =
////                    userDetailsService.loadUserByUsername(user.getUsername());
////
////            return jwtService.generateToken(userDetails);
////        }
////
////        return "Fail"; ///need to be handled
////    }
//        UserDetails userDetails =
//                userDetailsService.loadUserByUsername(user.getUsername());
//
//        return jwtService.generateToken(userDetails);
//    }
public LoginResponse verify(Users user) {

    Authentication authentication =
            authnManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(), user.getPassword()
                    )
            );


    UserDetails userDetails =
            (UserDetails) authentication.getPrincipal();

    LoginResponse response = new LoginResponse();

    response.setAccessToken(jwtService.generateAccessToken(userDetails));
    response.setRefreshToken(jwtService.generateRefreshToken(userDetails));

///   loadUserByUsername is removed bcz Spring already loaded the user during authentication.
//    UserDetails userDetails =
//            userDetailsService.loadUserByUsername(user.getUsername());

    //return jwtService.buildToken(userDetails);
    return response;
}

    public LoginResponse refreshToken(String refreshToken) {

        String username = jwtService.extractUserName(refreshToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!jwtService.validateToken(refreshToken, userDetails)) {
            throw new RuntimeException("Invalid refresh token");
        }

        LoginResponse response = new LoginResponse();

        response.setAccessToken(jwtService.generateAccessToken(userDetails));

        response.setRefreshToken(refreshToken);

        return response;
    }
}
