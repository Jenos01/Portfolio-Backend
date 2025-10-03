package com.example.demo.Service;


import com.example.demo.Entity.Certification;
import com.example.demo.Repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public List<Certification> getAllCertifications() {
        return certificationRepository.findAll();
    }

    public Certification addCertification(Certification certification) {
        return certificationRepository.save(certification);
    }
}
