package com.example.backstage.repository;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends BaseReporsitory<Student, Integer> {
    //查询所有学生
    @Query("from Student u")
    List<Student> findAllStudent();

    //查询指定id学生选课信息
    @Query(" select e.course  from Student s inner join Elective e on e.student.id=s.id where s.id=:id ")
    List<Course> FromIdGetCourse(@Param("id") int id);

    //查询指定课程id的所有学生
    @Query(" select s from Student s inner join Elective e on e.student.id=s.id where e.course.id=:id")
    List<Student> FromCourseGetStudent(@Param("id") int id);
}
