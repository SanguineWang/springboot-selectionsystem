package com.example.backstage.service;

import com.example.backstage.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;

    /**
     * 更新密码
     * 测试成功
     */
    @Test
    void update() {
        teacherService.update(2017000001, "666666");
    }

    /**
     * 更新其他信息，范围，指导学生数
     * 测试成功
     */
    @Test
    void testUpdate() {
        teacherService.update(2017000001, 12, Float.valueOf("80"));
    }

    /**
     * 添加方向
     * 测试成功
     */
    @Test
    void add() {
        teacherService.add(2017000001, "test");
    }

    /**
     * 修改方向
     * 测试成功
     */
    @Test
    void updateDirection() {
        teacherService.updateDirection(5, "test2");
    }

    /**
     * 创建课程。
     * 包括课程名称，权重。
     * test OK
     */
    @Test
    void testAdd() {
//        teacherService.add(2017000001, "test", Float.valueOf("1"));
    }

    /**
     * 修改课程信息
     * test OK
     */
    @Test
    void testUpdate1() {
        teacherService.update(5, "Docker学习小组", Float.valueOf("5"));
    }

    /**
     * 为指定课程添加 (基于学号，姓名，成绩)学生
     * 录入成绩
     * 再次调用删除所有指定课程的选课记录，相当于更新
     * test OK
     */
    @Test
    void testAdd1() {

        Student student = new Student();
        student.setId(2017214006);
        student.setName("q");
        student.setGrade(Float.valueOf("80"));

        Student student2 = new Student();
        student2.setId(2017214001);
        student2.setName("a");
        student2.setGrade(Float.valueOf("90"));

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);

        teacherService.add(1, students);
    }

    /**
     * 添加学生
     * test OK
     */
    @Test
    void testAdd2() {
        teacherService.add(2017000001, 2017214001, "wang");
    }

    /**
     * 验证密码
     * test OK
     */
    @Test
    void login() {

        log.debug("{}", teacherService.login(2017000001, "123"));
        log.debug("{}", teacherService.login(2017000001, "123456"));

    }

    /**
     * 获取有资格选课的同学
     * 成绩排名
     * test ok
     */
    @Test
    void get() {
        teacherService.get(2017000001, Float.valueOf("200")).forEach(s -> log.debug("{} {} \n{}", s.getGrade(), s.getName(), s));
    }

    /**
     * test ok
     * 加权成绩计算
     */
    @Test
    void start() {
        teacherService.start(2017000001);
    }

    @Test
    public void test_get() {
        log.debug("{}", teacherService.getStudentList(2017000001));
        log.debug("{}", teacherService.getStudentList(2017000000));
    }

    @Test
    public void test_get2() {
        teacherService.getDirectionList(2017000001).forEach(d -> log.debug("{}", d));
        log.debug("{}", teacherService.getDirectionList(2017000000));
    }

    @Test
    public void test_get3() {
        teacherService.getCourseList(2017000001).forEach(d -> log.debug("{}", d));
        teacherService.getCourseList(2017000000).forEach(c -> log.debug("{}", c));

    }

}