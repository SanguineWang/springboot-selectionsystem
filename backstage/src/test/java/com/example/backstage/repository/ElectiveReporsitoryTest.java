package com.example.backstage.repository;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Elective;
import com.example.backstage.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class ElectiveReporsitoryTest {
    @Autowired
    private ElectiveReporsitory electiveReporsitory;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test_add() {



    }

    @Test
    public void test_addelective() {
        Course course =  courseRepository.find(1);
        Course course2 =  courseRepository.find(2);
        Course course3 =  courseRepository.find(3);
        Student student = studentRepository.find(2017214001);
        Elective elective = new Elective();
        elective.setCourse(course);
        elective.setStudent(student);
        electiveReporsitory.save(elective);

        Elective elective2 = new Elective();
        elective2.setCourse(course2);
        elective2.setStudent(student);
        electiveReporsitory.save(elective2);

        Elective elective3 = new Elective();
        elective3.setCourse(course3);
        elective3.setStudent(student);
        electiveReporsitory.save(elective3);

    }

}