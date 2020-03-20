package com.example.backstage.repository;

import com.example.backstage.entity.Teacher;
import com.example.backstage.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TeacherRepositoryTest {
    @Autowired
    private TeacherService teacherService;


//    @Test
//    public void test_fetch() {
//        Teacher teacher = teacherService.getTeacher(2017000001);
//        log.debug(teacher.getStudentList().toString());
//    }
}