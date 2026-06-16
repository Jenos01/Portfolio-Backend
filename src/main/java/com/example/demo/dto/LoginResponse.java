package com.example.demo.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    //private String token;
    ///
    private String accessToken;
    private String refreshToken;
    ///
    private String message;
}
