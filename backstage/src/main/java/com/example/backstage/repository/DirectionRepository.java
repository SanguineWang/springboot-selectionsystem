package com.example.backstage.repository;

import com.example.backstage.entity.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DirectionRepository extends BaseReporsitory<Direction, Integer> {
    /**
     * 根据方向id 查方向
     * @param directionid
     * @return
     */
    @Query("select d from Direction d where  d.id=:directionid")
    Direction find(@Param("directionid") Integer directionid);

    /**
     * @param tid 教师工号
     * @return 方向集合
     */
    @Query("from Direction  d where d.teacher.id=:tid")
    List<Direction> findByTeacherId(@Param("tid")Integer tid);
}
