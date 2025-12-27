package com.example.demo.Controller;


import com.example.demo.Entity.Project;
import com.example.demo.Entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin("*")
@PreAuthorize("hasRole('VISITOR')")
@RestController
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {

//    @PostMapping
//    public Project addReview(@RequestBody Review review) {
//
//    }

}
