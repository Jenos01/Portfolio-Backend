package com.example.demo.Controller;


import com.example.demo.Entity.Project;
import com.example.demo.Service.ProjectService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }


    @PostMapping
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }
}
