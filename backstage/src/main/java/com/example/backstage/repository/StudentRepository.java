package com.example.backstage.repository;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Student;
import com.example.backstage.entity.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends BaseReporsitory<Student, Integer> {
    //查询所有学生
    @Query("from Student u")
    List<Student> findAll();

    //查询指定id学生选课信息
    @Query(" select e.course  from Student s inner join Elective e on e.student.id=s.id where s.id=:id ")
    List<Course> FromIdGetCourse(@Param("id") int id);

    //查询指定课程id的所有学生
    @Query(" select s from Student s inner join Elective e on e.student.id=s.id where e.course.id=:id")
    List<Student> FromCourseGetStudent(@Param("id") int id);

    //查询指定课程id的所有学生
    @Query(" select s from Student s inner join ChooseDirection e on e.student.id=s.id where e.direction.id=:id")
    List<Student> FromDirectionGetStudent(@Param("id") int id);


    //查询指定成绩范围limit , 指定老师id的学生,按照加权成绩倒序
    @Query("select s from Student s where s.grade>=:limit  order by s.grade desc ")
    List<Student> findByLimit(@Param("limit")Float limit);

    //查询指定老师的学生
    @Query("from Student  s where s.teacher.id=:tid")
    List<Student> findByTeacherId(@Param("tid")Integer tid);

    //查询指定学号的学生
    @Query("from Student s where s.user.number=:number")
    Student findByNumber(@Param("number")Integer number);

    //查询指定学号和指定老师的学生
    @Query("from Student s where s.user.number=:number and s.teacher.id=:tid")
    Student findByNumberAndTid(@Param("number")Integer number,@Param("tid")Integer tid);

//    //删除
//    @Modifying
//    @Transactional
//    @Query("delete from Elective e where e.course.id =:cid")
//    void remove(@Param("cid") Integer cid);
}
