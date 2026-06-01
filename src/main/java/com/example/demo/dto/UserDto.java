package com.example.demo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
//    Long id;
    String username;

    public UserDto(String username) {
        this.username = username;
    }
}
