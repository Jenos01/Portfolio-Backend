package com.example.demo.Service;


import com.example.demo.Entity.Rate;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.RateRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {  //will add some logic for this one

    private final RateRepository rateRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
//    public Rate addRate(Rate rate) {
//        return rateRepository.save(rate);
//    }
//
//
//    public Rate updateRate(Rate rate) {
//        return rateRepository.save(rate);
//    }

    public Double getAverage(Long projectId) {
        List<Rate> rates = rateRepository.findByProjectId(projectId);
        return rates.stream()
                .mapToInt(Rate::getRatePoints)
                .average()
                .orElse(0.0);
    }

    public Integer getUserRate(String username, Long projectId) {
        Users user = userRepository.findByUsername(username);
        Rate rate = rateRepository.findByUserIdAndProjectId(user.getId(), projectId);
        return rate != null ? rate.getRatePoints() : 0;
    }

public Rate saveRate(String username, Long projectId, Integer ratePoints) {

    Users user = userRepository.findByUsername(username);
    // check if user already rated this project
    Rate existing = rateRepository.findByUserIdAndProjectId(user.getId(), projectId);

    if (existing != null) {
        existing.setRatePoints(ratePoints);
        return rateRepository.save(existing);
    }

    Rate rate = new Rate();
    rate.setRatePoints(ratePoints);
    rate.setUser(user);
    rate.setProject(projectRepository.findById(projectId).orElseThrow());
    return rateRepository.save(rate);
}

    public Long getRatingCount(Long projectId) {
        return rateRepository.countByProjectId(projectId);
    }

    public void deleteUserRate(String username, Long projectId) {
        Users user = userRepository.findByUsername(username);
        if (user == null) return;

        Rate rate = rateRepository.findByUserIdAndProjectId(user.getId(), projectId);
        if (rate != null) {
            rateRepository.delete(rate);
        }
    }
}
