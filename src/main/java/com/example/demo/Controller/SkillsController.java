package com.example.demo.Controller;


import com.example.demo.Entity.Skills;
import com.example.demo.Service.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin("*")
@RestController
@RequestMapping("skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsService skillsService;



    @GetMapping
    public List<Skills> getAllSkills() {
        return skillsService.getAllSkills();
    }

    @GetMapping("/{skillId}")
    public Skills getSkillById(@PathVariable Long skillId) {
        return skillsService.getSkillById(skillId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Skills addSkills(@RequestBody Skills skills) {
        return skillsService.addSkills(skills);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("modify/{skillId}")
    public Skills updateSkill(@PathVariable Long skillId , @RequestBody Skills skill) {
        return skillsService.updateSkills(skillId,skill);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{skillId}")
    public void deleteSkill(@PathVariable Long skillId) {
        skillsService.deleteSkill(skillId);
    }
}
