package com.example.demo.utils;

import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class FirstTimeInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        createSkills();
        createProjects();
        createUsers();
    }

    private final SkillsRepository skillsRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final ReviewRepository reviewRepository;

    private Skills skill1,skill2,skill3,skill4,skill5,skill6;
    private Project project1,project2,project3,project4,project5,project6;
    private Users user1,user2,user3,user4,user5,user6;





    private void createSkills() {
        if (skillsRepository.count() == 0) {
            skill1 = skillsRepository.save(new Skills(null, "Angular", "https://img.icons8.com/?size=100&id=71257&format=png&color=000000"));
            skill2 = skillsRepository.save(new Skills(null, "Python", "https://img.icons8.com/?size=100&id=13441&format=png&color=000000"));
            skill3 = skillsRepository.save(new Skills(null, "C", "http://portfolio.test/projects/c_original_logo_icon_146611.svg"));
            skill4 = skillsRepository.save(new Skills(null, "Power BI", "https://img.icons8.com/?size=100&id=3sGOUDo9nJ4k&format=png&color=000000"));
            skill5 = skillsRepository.save(new Skills(null, "SpringBoot", "https://img.icons8.com/?size=100&id=90519&format=png&color=000000"));
            skill6 = skillsRepository.save(new Skills(null, "SQL", "https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/azuresqldatabase/azuresqldatabase-original.svg"));
        }
    }





    private void createProjects(){
            if(projectRepository.count() == 0) {

                ProjectSkills projectSkill1 = new ProjectSkills(null, null, skill1);
                ProjectSkills projectSkill2 = new ProjectSkills(null, null, skill2);
                ProjectSkills projectSkill3 = new ProjectSkills(null, null, skill3);
                ProjectSkills projectSkill4 = new ProjectSkills(null, null, skill4);
                ProjectSkills projectSkill5 = new ProjectSkills(null, null, skill5);
                ProjectSkills projectSkill6 = new ProjectSkills(null, null, skill6);
                ProjectSkills projectSkill7 = new ProjectSkills(null, null, skill5);
                ProjectSkills projectSkill8 = new ProjectSkills(null, null, skill2);


                project1 = new Project(null, "HR Dashboard", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda", "github.com/hrdashboard","http://portfolio.test/projects/dash.jpg", new ArrayList<>(List.of(projectSkill1,projectSkill2)));
                projectSkill1.setProject(project1);
                projectSkill2.setProject(project1);
                projectRepository.save(project1);

                project2 = new Project(null, "Heart Disease Prediction", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda", "github.com/heardiseaseprediction","http://portfolio.test/projects/dash.jpg", new ArrayList<>(List.of(projectSkill3,projectSkill4)));
                projectSkill3.setProject(project2);
                projectSkill4.setProject(project2);
                projectRepository.save(project2);

                project3 = new Project(null, "VR PFE Project", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda", "github.com/VRPfeProject","http://portfolio.test/projects/dash.jpg", new ArrayList<>(List.of(projectSkill5,projectSkill6)));
                projectSkill5.setProject(project3);
                projectSkill6.setProject(project3);
                projectRepository.save(project3);

                project4 = new Project(null, "Portfolio", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda", "github.com/portfolio","http://portfolio.test/projects/dash.jpg", new ArrayList<>(List.of(projectSkill7,projectSkill8)));
                projectSkill7.setProject(project4);
                projectSkill8.setProject(project4);
                projectRepository.save(project4);
            }
        }


        private void createUsers() {
            if(userRepository.count() == 0) {
                user1 = userRepository.save(new Users(null, "BouAli", "aminboualiaminbouali@gmail.com", "psd2001"));
                user1 = userRepository.save(new Users(null, "Med Amin", "jenosmoji@gmail.com", "psd3001"));
            }
        }


        private void createCertifications() {
            if (certificationRepository.count() == 0) {
                certificationRepository.save(new Certification(null,"Power BI Fundamentals","Datacamp",new Date(2023,7,4)));
                certificationRepository.save(new Certification(null,"Data Scientist with Python","Datacamp",new Date(2022,5,4)));
            }
        }

}
