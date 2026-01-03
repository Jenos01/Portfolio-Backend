package com.example.demo.Controller;


import com.example.demo.Entity.Rate;
import com.example.demo.Service.RateService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rate")
public class RateController {

    private final RateService rateService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public Rate addRate(Rate rate){
        return rateService.addRate(rate);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public Rate updateRate(Rate rate) {
        return rateService.updateRate(rate);
    }
}
