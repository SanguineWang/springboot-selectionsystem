package com.example.backstage.service;

import com.example.backstage.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
@Slf4j
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void test_addTeacher() {
        User user = new User();
        user.setName("SanguineWang");
        user.setNumber(2017000001);
        user.setPassword("123456");
        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacherService.addTeacher(teacher);
    }

    @Test
    public void test_addStudent() {
        User user = new User();
        user.setName("SanguineWang");
        user.setNumber(2017214005);
        user.setRole(User.Role.STUDENT);
        user.setPassword("123456");
        Student student = new Student();
        student.setUser(user);
        teacherService.addStudent(student);
    }

    @Test
    public void test_addCourse() {
        Course c = new Course();
        c.setName("web前端");
        c.setWeight(Float.valueOf("2.5"));
        teacherService.addCourse(c, 5);

        Course c2 = new Course();
        c2.setName("web系统框架");
        c2.setWeight(Float.valueOf("2.5"));
        teacherService.addCourse(c2, 5);

    }


    @Test
    public void test_addStudentsToCourse() {
        User user1 = new User();
        user1.setRole(User.Role.STUDENT);
        user1.setNumber(2017214002);
        Student student1 = new Student();
        student1.setGrade(Float.valueOf("98"));
        student1.setUser(user1);
        teacherService.addStudent(student1);
//        log.debug("\n{}", student1.getUser().getNumber());

        User user2 = new User();
        user2.setRole(User.Role.STUDENT);
        user2.setNumber(2017214003);
        Student student2 = new Student();
        student2.setGrade(Float.valueOf("88"));
        student2.setUser(user2);
        teacherService.addStudent(student2);
//        log.debug("\n{}", student2.getUser().getNumber());

        User user3 = new User();
        user3.setRole(User.Role.STUDENT);
        user3.setNumber(2017214004);
        Student student3 = new Student();
        student3.setGrade(Float.valueOf("78"));
        student3.setUser(user3);
        teacherService.addStudent(student3);
//        log.debug("\n{}", student3.getUser().getNumber());

        teacherService.addStudentsToCourse(1, List.of(student1, student2, student3));
    }

    @Test
    public void test_getCourses() {
        teacherService.getCourses(5).forEach(c -> {
            log.debug("\n {}", c.getName());
        });
    }

    @Test
    public void test_getDirection() {
        teacherService.getDirections(5).forEach(d -> {
            log.debug("\n {}", d.getName());
        });
    }
    @Test
    public void test_encoder() {
        log.debug(passwordEncoder.encode("123456") )   ;
    }


    @Test
    public void test_updatePassword() {
        teacherService.updatePassword(1,"123456","654321");
    }
    @Test
    public void test_updatePassword2() {

        teacherService.updatePassword(1,"654321","654321");
    }

}