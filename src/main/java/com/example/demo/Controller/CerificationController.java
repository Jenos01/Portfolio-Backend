package com.example.demo.Controller;


import com.example.demo.Entity.Certification;
import com.example.demo.Service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("certification")
@RequiredArgsConstructor
public class CerificationController {

    private final CertificationService certificationService;

    @GetMapping
    public List<Certification> getAllCertifications() {
        return certificationService.getAllCertifications();
    }

    @PostMapping
    public Certification addCertification(@RequestBody Certification certification) {
        return certificationService.addCertification(certification);
    }
}
