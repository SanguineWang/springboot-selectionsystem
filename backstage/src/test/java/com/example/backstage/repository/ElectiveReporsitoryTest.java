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
        Course course =  courseRepository.getOne(2);
        Student student = studentRepository.getOne(2017214001);
        Elective elective = new Elective();
        elective.setCourse(course);
        elective.setStudent(student);
        electiveReporsitory.save(elective);
    }

}