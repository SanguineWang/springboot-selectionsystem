package com.example.backstage.repository;

import com.example.backstage.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class RepositoryTest {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ElectiveReporsitory electiveReporsitory;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DirectionRepository directionRepository;

    @Test
    public void init() {
        User user1 = new User();
        user1.setRole(User.Role.TEACHER);
        user1.setNumber(2017000001);

        User user2 = new User();
        user2.setRole(User.Role.STUDENT);

        User user3 = new User();
        user3.setRole(User.Role.STUDENT);

        User user4 = new User();
        user4.setRole(User.Role.STUDENT);


        Teacher teacher1 = new Teacher();



        Course course1 = new Course();
        course1.setName("Java程序设计");
        course1.setWeight(Float.valueOf("2.5"));
        course1.setTeacher(teacher1);
        courseRepository.save(course1);

        Course course2 = new Course();
        course2.setName("Web开发技术");
        course2.setWeight(Float.valueOf("2.5"));
        course2.setTeacher(teacher1);
        courseRepository.save(course2);

        Course course3 = new Course();
        course3.setName("Web系统框架");
        course3.setWeight(Float.valueOf("2.5"));
        course3.setTeacher(teacher1);
        courseRepository.save(course3);

        Course course4 = new Course();
        course4.setName("移动终端设计");
        course4.setWeight(Float.valueOf("2.5"));
        course4.setTeacher(teacher1);
        courseRepository.save(course4);

        Course course5 = new Course();
        course5.setName("Linux-docker学习小组");
        course5.setWeight(Float.valueOf("5"));
        course5.setTeacher(teacher1);
        courseRepository.save(course5);


        Direction direction1 = new Direction();
        direction1.setName("当前企业实习项目");
        direction1.setTeacher(teacher1);
        directionRepository.save(direction1);

        Direction direction2 = new Direction();
        direction2.setName("钉钉/微信等小程序");
        direction2.setTeacher(teacher1);
        directionRepository.save(direction2);

        Direction direction3 = new Direction();
        direction3.setName("Web/微服务");
        direction3.setTeacher(teacher1);
        directionRepository.save(direction3);

        Direction direction4 = new Direction();
        direction4.setName("移动应用开发");
        direction4.setTeacher(teacher1);
        directionRepository.save(direction4);
    }

    @Test
    public void test_remove() {
        electiveReporsitory.remove(2);
    }

    @Test
    public void test_find() {
//        log.debug("{}", studentRepository.find(2017214000));

    }
}
