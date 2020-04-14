package com.example.backstage.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Test
    public void test_getGuidanceTeacher() {
        log.debug("\n teacher : {}", studentService.getGuidanceTeacher(10));
    }

    @Test
    public void test_getallStudent() {
        log.debug("\n student: {}", studentService.getAllStudent());
    }

}
