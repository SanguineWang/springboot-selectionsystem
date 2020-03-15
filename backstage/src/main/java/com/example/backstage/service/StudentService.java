package com.example.backstage.service;

import com.example.backstage.entity.Student;
import com.example.backstage.repository.StudentRepository;
import com.example.backstage.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudent(){
        return  studentRepository.findAll();
    }
}
