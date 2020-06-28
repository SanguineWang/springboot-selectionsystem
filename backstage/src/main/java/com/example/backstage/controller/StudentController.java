package com.example.backstage.controller;

import com.example.backstage.component.RequestComponent;
import com.example.backstage.entity.Direction;
import com.example.backstage.entity.Student;
import com.example.backstage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/student/")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RequestComponent requestComponent;

    @GetMapping("myInfo")
    public Map getmyInfo() {
        return Map.of("mine", studentService.getMyInfo(requestComponent.getUid()));
    }

    @GetMapping("teachers")
    public Map getTeachers() {
        return Map.of("teachers", studentService.getTeachers());
    }
    @GetMapping("teachers/{tid}")
    public Map getSheet(@PathVariable Integer tid) {
        return Map.of("selected",studentService.getTeacherSelected(tid),
                "electives", studentService.getScoreSheet(requestComponent.getUid(),tid));
    }
    /**
     * @param tid 选择成功后更新个人信息
     * @return
     */
    @GetMapping("teachers/{tid}/pick")
    public Map pickTeacher(@PathVariable Integer tid) {
        return Map.of("mine", studentService.pick(requestComponent.getUid(), tid));
    }

    @GetMapping("teachers/{tid}/directions")
    public Map getTeachers(@PathVariable Integer tid) {
        return Map.of("directions", studentService.getTeachersDirections(tid));
    }

    @PostMapping("myDirections")
    public Map ChooseDirection(@RequestBody List<Direction> directions) {
        studentService.ChooseDirections(requestComponent.getUid(), directions);
        return Map.of("chooseDirections", studentService.getMyDirection(requestComponent.getUid()));
    }

    @GetMapping("myDirections")
    public Map getMyDirections() {
        return Map.of("chooseDirections", studentService.getMyDirection(requestComponent.getUid()));
    }
}
