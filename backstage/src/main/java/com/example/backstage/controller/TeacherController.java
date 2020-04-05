package com.example.backstage.controller;

import com.example.backstage.entity.Course;
import com.example.backstage.service.TeacherService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/teacher/{tid}/")
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("main")
    public Map<String, Integer> getmain(@PathVariable Integer tid) {
        return teacherService.getPickAndLimit(tid);
    }

    @GetMapping("course")
    public Map getCourse(@PathVariable Integer tid) {
//        return Map.of("courses", teacherService.getCourseList(tid));
        return null;
    }

    @PostMapping("course")
    public Map addCourse(@PathVariable Integer tid,  @RequestBody Course course) {
        log.debug("{}{}", course.getName(), course.getWeight());
//        teacherService.add(tid, course);
//        return Map.of("courses", teacherService.getCourseList(tid));
        return  null;
    }

    @PatchMapping("course/{cid}")
    public Map updateCourse(@PathVariable Integer tid,@PathVariable Integer cid,@Validated @RequestBody Course course){
//        teacherService.update(cid,course.getName(),course.getWeight());
//        return Map.of("courses", teacherService.getCourseList(tid));
        return null;
    }

}
