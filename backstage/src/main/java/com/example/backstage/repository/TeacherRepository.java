package com.example.backstage.repository;

import com.example.backstage.entity.Direction;
import com.example.backstage.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherRepository extends BaseReporsitory<Teacher, UUID> {


    /**
     * 指定老师id获取老师
     * @param id
     * @return
     */
    @Query("from Teacher t where t.id=:id")
    Teacher find(@Param("id") Integer id);
}
