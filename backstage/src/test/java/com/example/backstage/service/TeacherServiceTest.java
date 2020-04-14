package com.example.backstage.service;

import com.example.backstage.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;

    @Test
    public void test_addTeacher() {
        User user = new User();
        user.setName("SanguineWang");
        user.setNumber(2017000001);
        user.setPassword("123456");
        Teacher teacher = new Teacher();
        teacherService.addTeacher(teacher, user);
    }

    @Test
    public void test_addStudent() {
        User user = new User();
        user.setName("SanguineWang");
        user.setNumber(2017214001);
        user.setPassword("123456");
        Student student = new Student();
        teacherService.addStudent(student, user);
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
    public void test_addDirection() {
        Direction direction1 = new Direction();
        direction1.setName("当前企业实习项目");
        teacherService.addDirection(direction1, 5);
    }

    @Test
    public void test_addStudentsToCourse() {
        User user1 = new User();
        user1.setRole(User.Role.STUDENT);
        user1.setNumber(2017214002);
        Student student1 = new Student();
        student1.setGrade(Float.valueOf("98"));
        teacherService.addStudent(student1, user1);
//        log.debug("\n{}", student1.getUser().getNumber());

        User user2 = new User();
        user2.setRole(User.Role.STUDENT);
        user2.setNumber(2017214003);
        Student student2 = new Student();
        student2.setGrade(Float.valueOf("88"));
        teacherService.addStudent(student2, user2);
//        log.debug("\n{}", student2.getUser().getNumber());

        User user3 = new User();
        user3.setRole(User.Role.STUDENT);
        user3.setNumber(2017214004);
        Student student3 = new Student();
        student3.setGrade(Float.valueOf("78"));
        teacherService.addStudent(student3, user3);
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
    public void test_getStudents() {
        teacherService.getStudentList(5).forEach(s -> {
            log.debug("\n {}", s.getUser().getNumber());
        });
    }

    @Test
    public void test_getPickAndLimit() {
        log.debug("\n{}", teacherService.getPickAndLimit(5));
    }

    @Test
    public void test_updatePassword() {
        teacherService.updatePassword(5, "666665");
    }

    @Test
    public void test_updateOthervariable() {
        teacherService.update(5, 12, Float.valueOf("80"));
    }

    @Test
    public void test_updateCourse() {
        teacherService.updateCourse(1, "java", Float.valueOf("2"));
    }

    @Test
    public void test_updateDirection() {
        teacherService.updateDirection(1, "当前企业实习项目");
    }

    @Test
    public void test_startAndCalculate() {
        teacherService.startAndCalculate(5);
    }

    @Test
    public void test_getQualifiedstudents() {
      teacherService.getQualifiedstudents(5, 80f)
              .forEach(s->log.debug("\n number :{} grade:{}",s.getUser().getNumber(),s.getGrade()));
    }

    @Test
    public void test_addStudentForTeacher(){
        User user=new User();
        user.setNumber(2017214001);
        user.setName("SanguineWang");
        Student student=new Student();
        teacherService.addStudent(student,user);
        teacherService.addStudentForTeacher(5,2017214001);
    }

    @Test
    public void test_removeStudent(){
        teacherService.removeStudent(5,2017214002);
        teacherService.removeStudent(5,2017214001);
    }
//
}