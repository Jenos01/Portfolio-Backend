package com.example.demo.Service;


import com.example.demo.Entity.Skills;
import com.example.demo.Repository.ProjectSkillsRepository;
import com.example.demo.Repository.SkillsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillsService {

    private final SkillsRepository skillsRepository;
    //private final ProjectSkillsRepository projectSkillsRepository;
    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }

    public Skills addSkills(Skills skills) {
        return skillsRepository.save(skills);
    }

    public Skills updateSkills(Long skillId, Skills skill) {
        return skillsRepository.save(skill);
    }

    public Skills getSkillById(Long skillId) {
        return skillsRepository.findById(skillId).get();
    }

    //@Transactional
    public void deleteSkill(Long skillId) {
        skillsRepository.deleteById(skillId);
        //projectSkillsRepository.deleteBySkillsId(skillId);

    }
}
