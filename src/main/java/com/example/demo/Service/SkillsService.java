package com.example.demo.Service;


import com.example.demo.Entity.Skills;
import com.example.demo.Repository.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillsService {

    private final SkillsRepository skillsRepository;

    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }

    public Skills addSkills(Skills skills) {
        return skillsRepository.save(skills);
    }
}
