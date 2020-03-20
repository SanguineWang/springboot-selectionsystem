package com.example.backstage.service;

import com.example.backstage.entity.Teacher;
import com.example.backstage.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * 添加教师
     * @param id
     * @param name
     * @param password
     */
    public void add(Integer id, String name, String password) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setPassword(password);
        teacherRepository.save(teacher);
    }


    public Teacher getTeacher(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

}
