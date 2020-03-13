package com.example.backstage.service;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Elective;
import com.example.backstage.entity.Student;
import com.example.backstage.repository.CourseRepository;
import com.example.backstage.repository.ElectiveReporsitory;
import com.example.backstage.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImportGradeService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ElectiveReporsitory electiveReporsitory;
    @Autowired
    private StudentRepository studentRepository;

    public void addElective( int Cno ,int Sno,float grade){
        Course course =  courseRepository.getOne(Cno);
        Student student = studentRepository.getOne(Sno);
        Elective elective = new Elective();
        elective.setCourse(course);
        elective.setStudent(student);
        elective.setGrade(grade);
        electiveReporsitory.save(elective);
    }
}
