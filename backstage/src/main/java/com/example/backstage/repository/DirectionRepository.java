package com.example.backstage.repository;

import com.example.backstage.entity.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository  extends BaseReporsitory<Direction,Integer>{
    @Query("select d from Direction d where d.teacher.id=:teacherid and d.id=:directionid")
    Direction find(@Param("teacherid")Integer teacherid,@Param("directionid")Integer directionid);
}
