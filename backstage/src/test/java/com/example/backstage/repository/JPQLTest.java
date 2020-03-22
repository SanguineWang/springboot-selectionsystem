package com.example.backstage.repository;

import com.example.backstage.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.geom.PathIterator;
import java.security.PublicKey;

@SpringBootTest
@Slf4j
public class JPQLTest {
    @Autowired
    private StudentRepository studentRepository;

   @Test
    public void allStudent(){
        log.debug("{}",studentRepository.findAll());
    }

    @Test
    public void  test_studentOfCourse(){
        log.debug("{}",studentRepository.FromCourseGetStudent(1));
    }

    @Test
    public void test_fromIdGetCoure(){
       log.debug("{}",studentRepository.FromIdGetCourse(2017214001));
    }
}
