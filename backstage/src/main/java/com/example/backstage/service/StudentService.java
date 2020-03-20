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



    public List<Student> getAllStudent(){
        return  studentRepository.findAll();
    }

    /**
     * 登录
     * @param id
     * @param password
     * @return
     */
    public Boolean login(Integer id,String password){
        Student student= studentRepository.findById(id).orElse(null);
        if (student!=null)
        {
            if (student.getPassword().equals(password))
            {
                return true;
            }
        }

        return false;
    }
}
