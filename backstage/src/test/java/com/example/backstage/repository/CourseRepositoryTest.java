package com.example.backstage.repository;

import com.example.backstage.entity.Course;
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
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;
    @Test
    public void test_add(){
        Course course1=new Course();
        course1.setName("Java程序设计");
        courseRepository.save(course1);

        Course course2=new Course();
        course2.setName("Web开发技术");
        courseRepository.save(course2);

        Course course3=new Course();
        course3.setName("Web系统框架");
        courseRepository.save(course3);

        Course course4=new Course();
        course4.setName("移动终端设计");
        courseRepository.save(course4);

        Course course5=new Course();
        course5.setName("Linux-docker学习小组");
        courseRepository.save(course5);
    }
}