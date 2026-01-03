package com.example.demo.Service;


import com.example.demo.Entity.Rate;
import com.example.demo.Repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateService {  //will add some logic for this one

    private final RateRepository rateRepository;

    public Rate addRate(Rate rate) {
        return rateRepository.save(rate);
    }


    public Rate updateRate(Rate rate) {
        return rateRepository.save(rate);
    }
}
