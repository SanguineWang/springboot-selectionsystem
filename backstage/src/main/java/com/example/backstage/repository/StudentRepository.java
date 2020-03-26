package com.example.backstage.repository;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Student;
import com.example.backstage.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends BaseReporsitory<Student, UUID> {
    //查询所有学生
    @Query("from Student u")
    List<Student> findAll();

    //查询指定id学生选课信息
    @Query(" select e.course  from Student s inner join Elective e on e.student.id=s.id where s.id=:id ")
    List<Course> FromIdGetCourse(@Param("id") int id);

    //查询指定课程id的所有学生
    @Query(" select s from Student s inner join Elective e on e.student.id=s.id where e.course.id=:id")
    List<Student> FromCourseGetStudent(@Param("id") int id);

    //查询指定成绩范围limit , 指定老师id的学生,按照加权成绩倒序
    @Query("select s from Student s where s.grade>=:limit  order by s.grade desc ")
    List<Student> find(@Param("limit")Float limit);

    /**
     * 指定学生id获取学生
     * @param id
     * @return
     */
    @Query("select  s from Student s where s.id=:id")
    Student find(@Param("id") Integer id);
}
