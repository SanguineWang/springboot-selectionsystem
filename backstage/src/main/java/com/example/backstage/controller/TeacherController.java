package com.example.backstage.controller;

import com.example.backstage.component.RequestComponent;
import com.example.backstage.entity.*;
import com.example.backstage.service.TeacherService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/teacher/")
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private RequestComponent requestComponent;

    @GetMapping("start")
    public Map getStartAndCalculate() {
        teacherService.startAndCalculate(requestComponent.getUid());
        return Map.of("students", teacherService.getQualifiedstudents(requestComponent.getUid()));
    }

    @GetMapping("qualified")
    public Map getQualified() {
        return Map.of("students", teacherService.getQualifiedstudents(requestComponent.getUid()));
    }

    //------------------------个人信息------------------------------------------------
    @GetMapping("selected")
    public Map getLimitedAndSelected() {
        return teacherService.getLimitedAndSelected(requestComponent.getUid());
    }

    //------------------------个人信息------------------------------------------------
    @GetMapping("settings")
    public Map getTeacher() {
        return Map.of("teacher", teacherService.getTeacher(requestComponent.getUid()));
    }

    @PatchMapping("settings")
    public Map setTeacher(@RequestBody Teacher teacher) {
        return Map.of("teacher", teacherService.updateTeacher(teacher));
    }
    @PostMapping("updatePassword")
    public void updatePw(@RequestBody List<String> pw) {
        //第一个是旧密码，第二个是新密码
        log.debug(pw.get(0));
        log.debug(pw.get(1));
        teacherService.updatePassword(requestComponent.getUid(),pw.get(0),pw.get(1));

    }

    // --------------------------我的学生----------------------------------------------
    @GetMapping("myStudents")
    public Map getStudents() {
        return Map.of("students", teacherService.getMyStudents(requestComponent.getUid()));
    }

    @PostMapping("addMyStudent")
    public Map addStudent(@RequestBody User user) {
        return Map.of("student", teacherService.addStudentForTeacher(requestComponent.getUid(), user));
    }

    @PatchMapping("removeMyStudents")
    public Map reomveStudents(@RequestBody List<Integer> studentIdList) {
        return Map.of("students", teacherService.removeMyStudents(studentIdList, requestComponent.getUid()));
    }

    //--------------------------课程集合-----------------------------------
    @GetMapping("courses")
    public Map getCourses() {
        return Map.of("courses", teacherService.getCourses(requestComponent.getUid()));
    }

    @PostMapping("courses")
    public Map addCourse(@RequestBody Course course) {
        teacherService.addCourse(course, requestComponent.getUid());
        return Map.of("courses", teacherService.getCourses(requestComponent.getUid()));
    }

    @DeleteMapping("courses/{cid}")
    public Map removeCourse(@PathVariable Integer cid) {
        teacherService.removeCourse(cid);
        return Map.of("courses", teacherService.getCourses(requestComponent.getUid()));
    }

    //-------------------------------课程详细-------------------------------------
    @PatchMapping("courses/{cid}")
    public Map updateCourse(@PathVariable Integer cid, @RequestBody Course course) {
        return Map.of("course", teacherService.updateCourse(cid, course));
    }

    @GetMapping("courses/{cid}")
    public Map getCourse(@PathVariable Integer cid) {
        return Map.of("course", teacherService.getCourse(cid));
    }


    @GetMapping("courses/{cid}/students")
    public Map getCoursesStudents(@PathVariable Integer cid) {
        return Map.of("electives", teacherService.getElectivesFromCourse(cid));
    }

    /**
     * 成功，获取学生和对应课程的成绩
     *
     * @param cid
     * @param students
     * @return
     */
    @PostMapping("courses/{cid}/students")
    public Map addCoursesStudents(@PathVariable Integer cid, @RequestBody List<Student> students) {
        teacherService.addStudentsToCourse(cid, students);
        return Map.of("electives", teacherService.getElectivesFromCourse(cid));
    }

//-------------------------------方向-------------------------------------

    @GetMapping("directions")
    public Map getDirections() {
        return Map.of("directions", teacherService.getDirections(requestComponent.getUid()));
    }

    @PostMapping("directions")
    public Map addDirection(@RequestBody Direction direction) {
        teacherService.addDirection(direction, requestComponent.getUid());
        return Map.of("directions", teacherService.getDirections(requestComponent.getUid()));
    }

    @DeleteMapping("directions/{did}")
    public Map removeDirection(@PathVariable Integer did) {
        teacherService.removeDirection(did);
        return Map.of("directions", teacherService.getDirections(requestComponent.getUid()));
    }

    @GetMapping("directions/{did}")
    public Map getDirection(@PathVariable Integer did) {
        return Map.of("direction", teacherService.getDirection(did));
    }

    @PatchMapping("directions/{did}")
    public Map addDirection(@PathVariable Integer did, @RequestBody Direction direction) {
        return Map.of("direction", teacherService.updateDirection(did, direction));
    }

    @GetMapping("directions/{did}/students")
    public Map getDirectionStudents(@PathVariable Integer did) {
        return Map.of("students", teacherService.getStudentsFromDirection(did));
    }

}
