package com.example.backstage.controller;

import com.example.backstage.entity.Student;
import com.example.backstage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/student/")
public class StudentController {
    @Autowired
    private StudentService studentService;


}
