package com.example.backstage.service;

import com.example.backstage.annotation.MyAuthority;
import com.example.backstage.entity.Student;
import com.example.backstage.entity.Teacher;
import com.example.backstage.repository.StudentRepository;
import com.example.backstage.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@MyAuthority(value = MyAuthority.MyAuthorityType.STUDENT)
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    /**
     * 登录
     *
     * @param sid
     * @param password
     * @return
     */
    public Boolean login(Integer sid, String password) {
        Student student = studentRepository.find(sid);
        if (student != null) {
            return student.getPassword().equals(password);
        }

        return false;
    }

    /**
     * 判断是否已经选过了
     * 选过了直接按钮置为不可点击。
     *
     * @param sid
     * @return
     */
    public Boolean everSelected(Integer sid) {
        return studentRepository.find(sid).getTeacher() != null;
    }

    /**
     * 选择导师，抢占式选择，
     * mysql默认事务隔离级别 ：可重复读
     * 多线程下就可能会发生 人数超过限制
     *
     * @param sid
     * @param tid
     */
    public void pick(Integer sid, Integer tid) {
        Student student = studentRepository.find(sid);
        Teacher teacher = teacherRepository.find(tid);
        int rest = teacher.getUpper_limit() - teacher.getStudentList().size();
        if (rest > 0) {
            student.setTeacher(teacher);
        }
    }
}
