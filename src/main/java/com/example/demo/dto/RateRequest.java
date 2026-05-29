package com.example.demo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateRequest {
    private Long projectId;
    private Integer ratePoints;
}