package com.example.backstage.repository;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test_add() {
        Student student1 = new Student();
        student1.setId(2017214001);
        student1.setName("a");
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setId(2017214002);
        student2.setName("b");
        studentRepository.save(student2);

        Student student3 = new Student();
        student3.setId(2017214003);
        student3.setName("c");
        studentRepository.save(student3);

        Student student4 = new Student();
        student4.setId(2017214004);
        student4.setName("d");
        studentRepository.save(student4);

        Student student5 = new Student();
        student5.setId(2017214005);
        student5.setName("e");
        studentRepository.save(student5);
    }

    @Test
    public void test_pick() {

    }
}