package com.example.backstage.service;

import com.example.backstage.annotation.MyAuthority;
import com.example.backstage.entity.*;
import com.example.backstage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
     * 获取教师教的课程集合
     *
     * @param tid
     * @return
     */
    public List<Course> getCourseList(Integer tid) {
        return Optional.ofNullable(courseRepository.findByTeacherId(tid))
                .orElse(List.of());
    }

    /**
     * 获取教师毕设方向
     *
     * @param tid
     * @return
     */
    public List<Direction> getDirectionList(Integer tid) {
        return Optional.ofNullable(directionRepository.findByTeacherId(tid))
                .orElse(List.of());
    }

    /**
     * 获取教师指导学生集合
     *
     * @param tid
     * @return
     */
    public List<Student> getStudentList(Integer tid) {
        return Optional.ofNullable(studentRepository.findByTeacherId(tid))
                .orElse(List.of());
    }

    /**
     * 获取教师已指导学生数和指导限制人数
     *
     * @param tid
     * @return
     */
    public Map<String, Integer> getSizeAndLimit(Integer tid) {
        Integer pick = Optional.ofNullable(studentRepository.findByTeacherId(tid))
                .orElse(List.of()).size();
        Integer limit = teacherRepository.find(tid).getUpper_limit();
        return Map.of("pick",pick,"limit",limit);
    }

    /**
     * 返回当前教师指定成绩范围的有资格的学生集合，按照加权成绩排序,(存入redis缓存)
     *
     * @param tid
     * @param weight
     * @return
     */
    public List<Student> get(Integer tid, Float weight) {
        Teacher teacher = teacherRepository.find(tid);//假的，没用
        return Optional.ofNullable(studentRepository.find(weight))
                .orElse(List.of());

    }

    /**
     * 为老师添加指定学生，添加已提前敲定的学生，直接占用导师名额
     *
     * @param tid
     * @param sid
     * @return
     */
    public void add(Integer tid, Integer sid, String name) {
        Student student = studentRepository.find(sid);
        Teacher teacher = teacherRepository.find(tid);
        if (teacher != null) {
            if (student != null) {
                int count = teacher.getUpper_limit() - teacher.getStudentList().size();
                if (count > 0)
                    student.setTeacher(teacher);
            } else {
                student = new Student();
                student.setId(sid);
                student.setName(name);
                student.setTeacher(teacher);
            }
            studentRepository.save(student);
        }
    }

    /**
     * 添加方向
     *
     * @param tid
     * @param dname
     */
    public void add(Integer tid, String dname) {
        Teacher teacher = teacherRepository.find(tid);
        Direction direction = new Direction();
        direction.setName(dname);
        direction.setTeacher(teacher);
        directionRepository.save(direction);
    }

    /**
     * 为指定课程添加学生
     * 录入成绩
     * 再次调用删除指定课程id(唯一) 的选课记录，相当于更新
     *
     * @param cid
     * @param students
     */
    public void add(Integer cid, List<Student> students) {

        Course course = courseRepository.find(cid);
        electiveReporsitory.remove(cid);
        students.forEach(a -> {

            if (studentRepository.find(a.getId()) == null) {
                a.setUuid(UUID.randomUUID());
                studentRepository.saveAndFlush(a);
            }
            Elective elective = new Elective();
            elective.setStudent(studentRepository.find(a.getId()));
            elective.setCourse(course);
            elective.setGrade(a.getGrade());
            electiveReporsitory.save(elective);
        });
    }

    /**
     * 创建课程。
     *
     * @param tid
     * @param course
     */
    public void add(Integer tid, Course course) {
        Teacher teacher = teacherRepository.find(tid);
        if (teacher != null) {
            course.setTeacher(teacher);
            courseRepository.save(course);
        }
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
     * 更新教师其他信息，范围，指导学生数
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
     * 启动双选
     * 计算学生加权成绩
     */
    public void start(Integer tid) {
        Teacher teacher = teacherRepository.find(tid);

        if (teacher != null) {
            List<Student> students = studentRepository.findAll();
            Float count = null;
            students.forEach(
                    s -> {
                        if (s.getElectiveList().size() != 0) {
                            s.getElectiveList().forEach(
                                    e -> {
                                        e.getStudent().setGrade(e.getCourse().getWeight() * e.getGrade());
                                    }
                            );
                            s.setGrade(s.getGrade() / s.getElectiveList().size());
                        }

                    }
            );
        }
    }
}
