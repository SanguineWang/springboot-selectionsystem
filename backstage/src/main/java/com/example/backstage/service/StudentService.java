package com.example.backstage.service;

import com.example.backstage.entity.Student;
import com.example.backstage.entity.Teacher;
import com.example.backstage.repository.StudentRepository;
import com.example.backstage.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;


    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
    /**
     * 获取导师
     * 判断是否已经选过了
     * 选过了直接按钮置为不可点击。
     *
     * @param sid studentId
     * @return Teacher
     */
    public Teacher getGuidanceTeacher(Integer sid) {
        return studentRepository.findById(sid).orElse(new Student()).getTeacher();
    }

    /**
     * 选择导师
     * mysql默认事务隔离级别 ：可重复读
     * 多线程下就可能会发生 人数超过限制
     * @param number student number
     * @param tid teacherid
     */
    public void pick(Integer number, Integer tid) {
        Student student = studentRepository.findByNumber(number);//非空
        teacherRepository.findById(tid).ifPresentOrElse(teacher -> {
            int rest = teacher.getUpper_limit() - teacher.getStudentList().size();
            if (rest > 0) {
                student.setTeacher(teacher);
                studentRepository.saveAndFlush(student);
                log.debug("\n student:{} 已选择 teacher {} - {}",studentRepository.refresh(student).getUser().getNumber(),
                        teacher.getUser().getNumber(),
                        teacher.getUser().getName());
            }else
                log.debug("人数已满");
        },()->{
            log.debug("教师不存在tid：{}",tid);
        });

    }
}
