package com.example.demo.Controller;


import com.example.demo.Entity.Certification;
import com.example.demo.Service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
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

    @DeleteMapping("/{id}")
    public void deleteCertification(@PathVariable Long id) {
         certificationService.deleteCertification(id);
    }

    @PutMapping("modify/{title}")
    public Certification modifyCertification(@PathVariable String title, @RequestBody Certification certification) {
        return certificationService.modifyCertification(title, certification);
    }
}
