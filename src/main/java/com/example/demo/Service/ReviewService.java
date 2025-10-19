package com.example.demo.Service;


import com.example.demo.Entity.Review;
import com.example.demo.Repository.RateRepository;
import com.example.demo.Repository.ReviewRepository;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RateRepository rateRepository;
    private final RateService rateService;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @PrePersist //not sure about the prepersist annotation
    public void forceOneRatePerUserForOneProject(Review review) {
        Review existedReview = reviewRepository.findById(review.getId()).orElse(null);
        
        if (existedReview != null) {
            if(existedReview.getRate() != null) {
                rateService.updateRate(review.getRate());
            }else{
                rateService.addRate(review.getRate());
            }
        }
    }

}
