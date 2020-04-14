package com.example.backstage.controller;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Direction;
import com.example.backstage.entity.Student;
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
@RequestMapping("api/teacher/{tid}/")
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("main")
    public Map<String, Integer> getmain(@PathVariable Integer tid) {
        return teacherService.getPickAndLimit(tid);
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        return teacherService.getAllStudents();
    }

    @GetMapping("courses")
    public Map getCourses(@PathVariable Integer tid) {
        return Map.of("courses", teacherService.getCourses(tid));
    }

    @GetMapping("courses/{cid}")
    public Map getCourse(@PathVariable Integer cid) {
        return Map.of("course", teacherService.getCourse(cid),
                "students", teacherService.getStudentsFromCourse(cid));
    }

    @GetMapping("directions")
    public Map getDirections(@PathVariable Integer tid) {
        return Map.of("directions", teacherService.getDirections(tid));
    }

    @GetMapping("directions/{did}")
    public Map getDirection(@PathVariable Integer did) {
        return Map.of("direction", teacherService.getDirection(did),
                "students", teacherService.getStudentsFromDirection(did));
    }

    @PostMapping("course")
    public Map addCourse(@PathVariable Integer tid, @RequestBody Course course) {
        log.debug("{}{}", course.getName(), course.getWeight());
        teacherService.addCourse(course, tid);
        return Map.of("courses", teacherService.getCourses(tid));
    }
    @PostMapping("direction")
    public Map addDirection(@PathVariable Integer tid, @RequestBody Direction direction) {
        log.debug("{}{}", direction.getName(), direction.getWeight());
        teacherService.addDirection(direction, tid);
        return Map.of("directions", teacherService.getDirections(tid));
    }

    @PatchMapping("course/{cid}")
    public Map updateCourse(@PathVariable Integer tid, @PathVariable Integer cid, @RequestBody Course course) {
        teacherService.updateCourse(cid, course.getName(), course.getWeight());
        return Map.of("courses", teacherService.getCourses(tid));
    }

    @PatchMapping("direction/{did}")
    public Map addDirection(@PathVariable Integer tid, @PathVariable Integer did, @RequestBody Direction direction) {
        teacherService.updateDirection(did, direction.getName());
        return Map.of("directions", teacherService.getDirections(tid));
    }

    @DeleteMapping("course/{cid}")
    public Map  removeCourse(@PathVariable Integer tid, @PathVariable Integer cid ){
        teacherService.removeCourse(cid);
        return Map.of("courses", teacherService.getCourses(tid));
    }

    @DeleteMapping("direction/{did}")
    public Map removeDirection(@PathVariable Integer tid, @PathVariable Integer did){
        teacherService.removeDirection(did);
        return Map.of("directions", teacherService.getDirections(tid));
    }
}
