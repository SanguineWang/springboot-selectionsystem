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
     * @param tid 教师工号
     * @return 方向集合
     */
    @Query("from Direction  d where d.teacher.id=:tid")
    List<Direction> findByTeacherId(@Param("tid")Integer tid);

    /**查询一个学生的方向
     * @return 方向集合
     */
    @Query("select cd.direction from ChooseDirection cd where cd.student.id=:sid")
    List<Direction> findDirectionsByStudentId(@Param("sid")Integer sid);
}
