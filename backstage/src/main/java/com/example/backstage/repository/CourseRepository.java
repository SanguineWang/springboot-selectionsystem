package com.example.backstage.repository;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends BaseReporsitory<Course, Integer> {
    /**
     * 查询教师指定课程名的课程
     * @param tid
     * @param cname
     * @return
     */
    @Query("from Course c where c.name=:cname and c.teacher.id=:tid")
    Course findByName(@Param("cname")String cname ,@Param("tid")Integer tid);
    /**
     * @param tid 教师工号
     * @return 课程集合
     */
    @Query("from Course c where c.teacher.id=:tid")
    List<Course> findByTeacherId(@Param("tid") Integer tid);




}
