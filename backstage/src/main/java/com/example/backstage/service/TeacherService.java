package com.example.backstage.service;

import com.example.backstage.entity.*;
import com.example.backstage.repository.*;
import javassist.expr.NewArray;
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
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ElectiveReporsitory electiveReporsitory;

    /**
     * 更新密码
     *
     * @param id
     * @param password
     */
    public void update(int id, String password) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            teacher.setPassword(password);
            teacherRepository.save(teacher);
        }
    }

    /**
     * 更新其他信息，范围，指导学生数
     *
     * @param id
     * @param upper_limit
     * @param mark_limit
     */
    public void update(int id, Integer upper_limit, Float mark_limit) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            teacher.setUpper_limit(upper_limit);
            teacher.setMark_limit(mark_limit);
            teacherRepository.save(teacher);
        }
    }

    /**
     * 添加方向
     *
     * @param id
     * @param name
     */
    public void add(int id, String name) {
        Direction direction = new Direction();
        direction.setName(name);
        directionRepository.save(direction);
    }

    /**
     * 修改毕设方向
     *
     * @param teacherid
     * @param directionid
     * @param name
     */
    public void update(int teacherid, int directionid, String name) {
        Direction d = directionRepository.find(teacherid, directionid);
        d.setName(name);
        directionRepository.save(d);
    }

    /**
     * 创建课程。
     * 包括课程名称，权重。
     *
     * @param tid
     * @param courseName
     * @param weight
     */
    public void add(int tid, String courseName, Float weight) {
        Teacher teacher = teacherRepository.findById(tid).orElse(null);
        if (teacher != null) {
            Course course = new Course();
            course.setName(courseName);
            course.setWeight(weight);
            course.setTeacher(teacher);
        }
    }

    /**
     * 修改课程信息
     *
     * @param tid
     * @param cid
     * @param courseName
     * @param weight
     */
    public void update(int tid, int cid, String courseName, Float weight) {
        Course course = courseRepository.find(cid, tid);
        course.setName(courseName);
        course.setWeight(weight);
        courseRepository.save(course);
    }

    /**
     * 为指定课程添加学生
     * 录入成绩
     * 再次调用删除以前的，相当于更新
     *
     * @param tid
     * @param cid
     * @param students
     */
    public void add(int tid, int cid, List<Student> students) {

        Course course = courseRepository.find(cid, tid);
        electiveReporsitory.deleteAllByCourseId(cid);
        students.forEach(a -> {
            if (studentRepository.findById(a.getId()).isEmpty()) {

                studentRepository.save(a);
            }
            Elective elective = new Elective();
            elective.setStudent(a);
            elective.setCourse(course);
            elective.setGrade(a.getGrade());
            electiveReporsitory.save(elective);
        });
    }

    /**
     * 为老师添加指定学生
     * 添加已提前敲定的学生，直接占用导师名额
     *
     * @param tid
     * @param sid
     * @return
     */
    public String add(int tid, int sid) {
        Student student = studentRepository.findById(sid).orElse(null);
        Teacher teacher = teacherRepository.findById(tid).orElse(null);
        if (student == null) {
            return "The student was not found";
        } else {
            student.setTeacher(teacher);
            return "Add success";
        }
    }

    /**
     * 登录
     * @param id
     * @param password
     * @return
     */
    public Boolean login(Integer id, String password) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            return teacher.getPassword().equals(password);
        }
        return false;
    }

}
