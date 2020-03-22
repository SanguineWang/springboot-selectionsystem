package com.example.backstage.service;

import com.example.backstage.annotation.MyAuthority;
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
@MyAuthority(value = MyAuthority.MyAuthorityType.TEACHER)
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
     * @param tid
     * @param password
     */
    public void update(Integer tid, String password) {
        Teacher teacher = teacherRepository.find(tid);
        if (teacher != null) {
            teacher.setPassword(password);
            teacherRepository.save(teacher);
        }
    }

    /**
     * 更新其他信息，范围，指导学生数
     *
     * @param tid
     * @param upper_limit
     * @param mark_limit
     */
    public void update(Integer tid, Integer upper_limit, Float mark_limit) {
        Teacher teacher = teacherRepository.find(tid);
        if (teacher != null) {
            teacher.setUpper_limit(upper_limit);
            teacher.setMark_limit(mark_limit);
            teacherRepository.save(teacher);
        }
    }

    /**
     * 添加方向
     *
     * @param tid
     * @param name
     */
    public void add(Integer tid, String name) {
        Teacher teacher = teacherRepository.find(tid);
        Direction direction = new Direction();
        direction.setName(name);
        direction.setTeacher(teacher);
        directionRepository.save(direction);
    }

    /**
     * 修改毕设方向
     *
     * @param did
     * @param name
     */
    public void updateDirection(Integer did, String name) {


        Direction d = directionRepository.find(did);
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
    public void add(Integer tid, String courseName, Float weight) {
        Teacher teacher = teacherRepository.find(tid);
        if (teacher != null) {
            Course course = new Course();
            course.setName(courseName);
            course.setWeight(weight);
            course.setTeacher(teacher);
            courseRepository.save(course);
        }
    }

    /**
     * 修改课程信息
     *
     * @param cid
     * @param courseName
     * @param weight
     */
    public void update(Integer cid, String courseName, Float weight) {
        Course course = courseRepository.find(cid);
        course.setName(courseName);
        course.setWeight(weight);
        courseRepository.save(course);
    }

    /**
     * 为指定课程添加学生
     * 录入成绩
     * 再次调用删除所有指定教师，指定课程的选课记录，相当于更新
     *
     * @param tid
     * @param cid
     * @param students
     */
    public void add(Integer tid, Integer cid, List<Student> students) {

        Course course = courseRepository.find(cid);
        electiveReporsitory.remove(cid, tid);
        students.forEach(a -> {
            if (studentRepository.find(a.getId()) == null) {
                studentRepository.save(a);
            } else {
                Elective elective = new Elective();
                elective.setStudent(a);
                elective.setCourse(course);
                elective.setGrade(a.getGrade());
                electiveReporsitory.save(elective);
            }
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
    public void add(int tid, int sid) {
        Student student = studentRepository.find(sid);
        Teacher teacher = teacherRepository.find(tid);

        if (student != null && teacher != null) {

            int count = teacher.getUpper_limit() - teacher.getStudentList().size();
            if (count >= 0)
                student.setTeacher(teacher);

        }
    }

    /**
     * 登录
     *
     * @param tid
     * @param password
     * @return
     */
    public Boolean login(Integer tid, String password) {
        Teacher teacher = teacherRepository.find(tid);
        if (teacher != null) {
            return teacher.getPassword().equals(password);
        }
        return false;
    }

    /**
     * 返回当前教师指定成绩范围的
     * 有资格的学生集合，按照加权成绩排序
     *
     * @param tid
     * @param weight
     * @return
     */
    public List<Student> get(Integer tid, Float weight) {
        Teacher teacher = teacherRepository.find(tid);
        List<Student> students = null;
        if (teacher != null) {

            students = studentRepository.find( weight,tid);
        }
        return students;
    }

    /**
     * 启动双选
     * 计算学生加权成绩
     */
    public void start(int tid) {
        Teacher teacher = teacherRepository.find(tid);
        if (teacher != null) {
            List<Student> students = teacher.getStudentList();
            Float count = null;
            students.forEach(
                    s -> {
                        s.getElectiveList().forEach(
                                e -> {
                                    e.getStudent().setGrade(e.getCourse().getWeight() * e.getGrade());
                                }
                        );
                        s.setGrade(s.getGrade() / s.getElectiveList().size());
                    }
            );
        }
    }
}
