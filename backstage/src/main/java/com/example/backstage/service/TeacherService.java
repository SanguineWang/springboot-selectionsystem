package com.example.backstage.service;

import com.example.backstage.entity.Student;
import com.example.backstage.entity.Teacher;
import com.example.backstage.repository.StudentRepository;
import com.example.backstage.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;


    public  void  updateTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Teacher getTeacher(int id) {
        return teacherRepository.findById(id).orElse(null);
    }
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }



}
