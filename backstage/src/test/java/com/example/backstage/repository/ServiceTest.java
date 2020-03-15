package com.example.backstage.repository;

import com.example.backstage.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ServiceTest {
    @Autowired
    private StudentService studentService;


    @Test
    public void init(){

    }
    @Test
    public void test_allstudent() {
       studentService.getAllStudent().forEach(a-> log.debug("{} {}",a.getId(),a.getName()));
    }
    @Test
    public  void test_allstudent2(){
        log.debug("{}",studentService.getAllStudent());
    }
}
