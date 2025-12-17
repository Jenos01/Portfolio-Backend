package com.example.demo.utils;

import com.example.demo.Entity.*;
import com.example.demo.Enums.Role;
import com.example.demo.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class FirstTimeInit implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        createSkills();
        createProjects();
        createUsers();
        createCertifications();
        createRates();
        createComments();
        createReviews();
    }

    private final SkillsRepository skillsRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final ReviewRepository reviewRepository;
    private final RateRepository rateRepository;
    private final CommentRepository commentRepository;

    private Skills skill1,skill2,skill3,skill4,skill5,skill6,skill7,skill8,skill9;
    private Project project1,project2,project3,project4,project5,project6;
    private Users user1,user2,user3,user4,user5,user6;





    private void createSkills() {
        if (skillsRepository.count() == 0) {
            skill1 = skillsRepository.save(new Skills(null, "Angular", "https://img.icons8.com/?size=100&id=71257&format=png&color=000000",new ArrayList<>()));
            skill2 = skillsRepository.save(new Skills(null, "Python", "https://img.icons8.com/?size=100&id=13441&format=png&color=000000",new ArrayList<>()));
            skill3 = skillsRepository.save(new Skills(null, "C", "http://portfolio.test/Skills/icons8-c-96.png",new ArrayList<>()));
            skill4 = skillsRepository.save(new Skills(null, "Power BI", "https://img.icons8.com/?size=100&id=3sGOUDo9nJ4k&format=png&color=000000",new ArrayList<>()));
            skill5 = skillsRepository.save(new Skills(null, "SpringBoot", "https://img.icons8.com/?size=100&id=90519&format=png&color=000000",new ArrayList<>()));
            skill6 = skillsRepository.save(new Skills(null, "SQL", "https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/azuresqldatabase/azuresqldatabase-original.svg",new ArrayList<>()));
            skill7 = skillsRepository.save(new Skills(null, "PostgreSQL", "http://portfolio.test/Skills/icons8-postgresql-96.png",new ArrayList<>()));
            skill8 = skillsRepository.save(new Skills(null, "C#", "http://portfolio.test/Skills/C_Sharp.png",new ArrayList<>()));
            skill9 = skillsRepository.save(new Skills(null, "Unity", "http://portfolio.test/Skills/images.png",new ArrayList<>()));
            /// Java, HTML, CSS, SCSS, TypeScript, ...

        }
    }





    private void createProjects(){
            if(projectRepository.count() == 0) {

                ProjectSkills projectSkill1 = new ProjectSkills(null, null, skill1);//angular
                ProjectSkills projectSkill2 = new ProjectSkills(null, null, skill2);//python
                ProjectSkills projectSkill3 = new ProjectSkills(null, null, skill3);//C
                ProjectSkills projectSkill4 = new ProjectSkills(null, null, skill4);//PowerBi
                ProjectSkills projectSkill5 = new ProjectSkills(null, null, skill5);//Springboot
                ProjectSkills projectSkill6 = new ProjectSkills(null, null, skill6);//SQL
                ProjectSkills projectSkill7 = new ProjectSkills(null, null, skill7);//PostgreSQL
                ProjectSkills projectSkill8 = new ProjectSkills(null, null, skill8);//C#
                ProjectSkills projectSkill9 = new ProjectSkills(null, null, skill9);//Unity
                /// ----------------------
                ProjectSkills projectSkill12 = new ProjectSkills(null, null, skill1);//angular
                ProjectSkills projectSkill52 = new ProjectSkills(null, null, skill5);//Springboot
                ProjectSkills projectSkill72 = new ProjectSkills(null, null, skill7);//PostgreSQL
                /// ----------------------
                ProjectSkills projectSkill13 = new ProjectSkills(null, null, skill1);//angular
                ProjectSkills projectSkill53 = new ProjectSkills(null, null, skill5);//Springboot
                ProjectSkills projectSkill73 = new ProjectSkills(null, null, skill7);//PostgreSQL
                /// ----------------------
                ProjectSkills projectSkill44 = new ProjectSkills(null, null, skill4);//PowerBi
                /// ----------------------
                ProjectSkills projectSkill45 = new ProjectSkills(null, null, skill4);//PowerBi


                ///************************
                project1 = new Project(null, "POS System","Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda","https://github.com/Jenos01/POS-System","http://portfolio.test/projects/dash.jpg",new ArrayList<>(List.of(projectSkill1, projectSkill5, projectSkill7)));
                projectSkill1.setProject(project1);
                projectSkill5.setProject(project1);
                projectSkill7.setProject(project1);
                projectRepository.save(project1);

                ///
                project2 = new Project(null, "Portfolio", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda", "github.com/portfolio","http://portfolio.test/projects/dash.jpg", new ArrayList<>(List.of(projectSkill12,projectSkill4,projectSkill52,projectSkill72)));
                projectSkill12.setProject(project2);
                projectSkill4.setProject(project2);
                projectSkill52.setProject(project2);
                projectSkill72.setProject(project2);
                projectRepository.save(project2);

                ///
                project3 = new Project(null, "Kitchen And Bedroom Virtual Tour", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda", "github.com/VRPfeProject","http://portfolio.test/projects/dash.jpg", new ArrayList<>(List.of(projectSkill13,projectSkill53,projectSkill73,projectSkill8,projectSkill9)));
                projectSkill13.setProject(project3);
                projectSkill53.setProject(project3);
                projectSkill73.setProject(project3);
                projectSkill8.setProject(project3);
                projectSkill9.setProject(project3);
                projectRepository.save(project3);

                ///
                project4 = new Project(null, "Heart Disease Prediction", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda", "github.com/heardiseaseprediction","http://portfolio.test/projects/dash.jpg", new ArrayList<>(List.of(projectSkill2,projectSkill44)));
                projectSkill2.setProject(project4);
                projectSkill44.setProject(project4);
                projectRepository.save(project4);

                ///
                project5 = new Project(null, "HR Dashboard", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis eos ipsa assumenda", "github.com/hrdashboard","http://portfolio.test/projects/dash.jpg", new ArrayList<>(List.of(projectSkill45)));
                projectSkill45.setProject(project5);
                projectRepository.save(project5);

            }
        }


        private void createUsers() {
            if(userRepository.count() == 0) {
                user1 = userRepository.save(new Users(null, "BouAli", "aminboualiaminbouali@gmail.com", "psd2001",Role.ROLE_VISITOR, true));
                user2 = userRepository.save(new Users(null, "Med Amin", "jenosmoji@gmail.com", "psd3001", Role.ROLE_USER, true));
            }
        }


        private void createCertifications() {
            if (certificationRepository.count() == 0) {
                certificationRepository.save(new Certification(null,"Power BI Fundamentals","Datacamp", LocalDate.of(2023,7,4),"http://portfolio.test/Certifications/Power%20BI%20Fundamentals.png"));
                certificationRepository.save(new Certification(null,"Data Scientist with Python","Datacamp", LocalDate.of(2022,5,8),"http://portfolio.test/Certifications/Data%20Scientist%20With%20Python.png"));
                certificationRepository.save(new Certification(null,"EF SET C1 Advanced English Level","EF SET", LocalDate.of(2023,2,22),"http://portfolio.test/Certifications/EF%20SET%20C1.png"));
                certificationRepository.save(new Certification(null,"Hult Prize Regional Finalist","Hult Prize", LocalDate.of(2021,4,27),"http://portfolio.test/Certifications/hult%20prize.png"));
            }
        }



    private Rate rate1, rate2, rate3, rate4, rate5;

    private void createRates() {
        if (rateRepository.count() == 0) {
            rate1 = rateRepository.save(new Rate(null, 5)); // 5 stars
            rate2 = rateRepository.save(new Rate(null, 4));
            rate3 = rateRepository.save(new Rate(null, 3));
            rate4 = rateRepository.save(new Rate(null, 3));
            rate5 = rateRepository.save(new Rate(null, 2));

        }
    }

    private void createReviews() {
        if (reviewRepository.count() == 0) {
            Review review1 = new Review(null, project1, null, user1, rate1);
            Review review2 = new Review(null, project1, null, user2, rate2);
            Review review3 = new Review(null, project2, null, user2, rate3);
            Review review4 = new Review(null, project2, null, user2, rate4);
            Review review5 = new Review(null, project3, null, user2, rate5);
            Review review6 = new Review(null, project1, comment1, user1, null);
            Review review7 = new Review(null, project1, comment2, user2, null);
            Review review8 = new Review(null, project2, comment3, user3, null);

            reviewRepository.saveAll(List.of(review1, review2,review3,review4,review5,review6,review7,review8));
        }
    }

    private Comment comment1, comment2, comment3, comment4, comment5;

//    public void createComments() {
//         comment1 = new Comment(null, "Nice Project, Keep up the nice work");
//         comment2 = new Comment(null, "Great Project!");
//         comment3 = new Comment(null, "Great Project!, Keep Growing");
//
//         commentRepository.saveAll(List.of(comment1, comment2, comment3));
//
//  }
private void createComments() {
    if (commentRepository.count() == 0) {
        Comment comment1 = commentRepository.save(new Comment(null, "Great project!"));
        Comment comment2 = commentRepository.save(new Comment(null, "Needs improvement."));
        Comment comment3 = commentRepository.save(new Comment(null, "Excellent work!"));


        //List<Comment> comments = List.of(comment1, comment2, comment3);
    }
}




}
