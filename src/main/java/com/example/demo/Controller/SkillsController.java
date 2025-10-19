package com.example.demo.Controller;


import com.example.demo.Entity.Skills;
import com.example.demo.Service.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsService skillsService;

    @GetMapping
    public List<Skills> getAllSkills() {
        return skillsService.getAllSkills();
    }

    @PostMapping
    public Skills addSkills(@RequestBody Skills skills) {
        return skillsService.addSkills(skills);
    }
}
