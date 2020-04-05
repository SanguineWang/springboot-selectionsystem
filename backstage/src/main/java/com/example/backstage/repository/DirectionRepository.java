package com.example.backstage.repository;

import com.example.backstage.entity.Course;
import com.example.backstage.entity.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DirectionRepository extends BaseReporsitory<Direction, Integer> {
    /**
     * 查询教师指定方向名的方向
     * @param tid 教师id
     * @param dname 方向名
     * @return Direction
     */
    @Query("from Direction d where d.name=:dname and d.teacher.id=:tid")
    Direction findByName(@Param("dname")String dname , @Param("tid")Integer tid);

    /**
     * @param tid 教师工号
     * @return 方向集合
     */
    @Query("from Direction  d where d.teacher.id=:tid")
    List<Direction> findByTeacherId(@Param("tid")Integer tid);
}
