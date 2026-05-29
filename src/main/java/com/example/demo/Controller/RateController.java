package com.example.demo.Controller;


import com.example.demo.Entity.Rate;
import com.example.demo.Service.RateService;
import com.example.demo.dto.RateRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("rate")
public class RateController {

    private final RateService rateService;

//    @PreAuthorize("hasRole('USER')")
//    @PostMapping
//    public Rate addRate(@RequestBody Rate rate){
//        return rateService.addRate(rate);
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @PutMapping
//    public Rate updateRate(@RequestBody Rate rate) {
//        return rateService.updateRate(rate);
//    }
@GetMapping("average/{projectId}")
public Double getAverage(@PathVariable Long projectId) {
    return rateService.getAverage(projectId);
}

    @PreAuthorize("hasRole('USER')")
    @GetMapping("user/{projectId}")
    public Integer getUserRate(@PathVariable Long projectId, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return rateService.getUserRate(username, projectId);
    }


@PreAuthorize("hasRole('USER')")
@PostMapping
public Rate saveRate(@RequestBody RateRequest request, Authentication authentication) {
    String username = (String) authentication.getPrincipal();
    return rateService.saveRate(username, request.getProjectId(), request.getRatePoints());
}

    @GetMapping("/count/{projectId}")
    public Long getRatingCount(@PathVariable Long projectId) {
        return rateService.getRatingCount(projectId);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{projectId}")
    public void deleteUserRate(
            @PathVariable Long projectId,
            Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        rateService.deleteUserRate(username, projectId);
    }
}
