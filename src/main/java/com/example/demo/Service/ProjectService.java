package com.example.demo.Service;


import com.example.demo.Entity.Project;
import com.example.demo.Entity.ProjectSkills;
import com.example.demo.Repository.ProjectRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project addProject(Project project) {
        for (ProjectSkills ps : project.getProjectSkills()) {
            ps.setProject(project);
        }
        return projectRepository.save(project);
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public Project modifyProject(String projectName, Project project) {
        //project.getProjectSkills().clear();
        for (ProjectSkills ps : project.getProjectSkills()) {
            ps.setProject(project);
        }

        return projectRepository.save(project);

//        Project existedProject = projectRepository.findById(updatedProject.getId()).orElse(null);
//        if (existedProject != null) {
//            updatedProject.getProjectSkills().clear();
//            //project.getProjectSkills().addAll(existedProject.getProjectSkills());
//            for (ProjectSkills ps : updatedProject.getProjectSkills()) {
//                ps.setProject(existedProject);
//            }
//
//        }
//
//        return projectRepository.save(updatedProject);
    }
}