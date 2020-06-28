package com.example.backstage.repository;

import com.example.backstage.entity.Elective;
import com.example.backstage.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ElectiveReporsitory extends BaseReporsitory<Elective, Integer> {

    /**
     * 删除指定课程id的选课记录
     *
     * @param cid
     */
    @Modifying
    @Transactional
    @Query("delete from Elective e where e.course.id =:cid")
    void remove(@Param("cid") Integer cid);

    /**
     * @return 所有课程的选课人数
     */
    @Query("select e.course.name, count (e.id) as number from Elective e group by e.course.id order by number")
    List find();

    /**
     * 查询指定课程的全部学生
     *
     * @return
     */
    @Query("select e.student from Elective e where e.course.id=:cid")
    List<Student> findStudentsByCourseId(@Param("cid") Integer cid);

}
