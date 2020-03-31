package com.example.backstage.repository;

import com.example.backstage.entity.Elective;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ElectiveReporsitory extends BaseReporsitory<Elective, UUID> {

    /**
     * 删除指定教师id，课程id的选课记录
     * @param cid
     */
    @Modifying
    @Transactional
    @Query("delete from Elective e where e.course.id =:cid")
    void remove(@Param("cid") Integer cid);




}
