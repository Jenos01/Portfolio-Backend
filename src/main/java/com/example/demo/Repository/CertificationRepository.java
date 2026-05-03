package com.example.demo.Repository;

import com.example.demo.Entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    Certification findCertificationBySlug(String slug);
}
