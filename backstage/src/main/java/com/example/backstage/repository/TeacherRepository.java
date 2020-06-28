package com.example.backstage.repository;

import com.example.backstage.entity.Direction;
import com.example.backstage.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherRepository extends BaseReporsitory<Teacher, Integer> {


    /**
     * 指定老师工号获取老师
     * @param
     * @return
     */
    @Query("from Teacher t where t.user.number=:number")
    Teacher findByNumber(@Param("number") Integer number);
}
