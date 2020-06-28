package com.example.backstage.repository;

import com.example.backstage.entity.ChooseDirection;
import com.example.backstage.repository.impl.BaseReporsitoryImpl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ChooseDirectionReporsitory extends BaseReporsitory<ChooseDirection, Integer> {

    /**
     * 删除指定学生sid的选方向记录
     */
    @Modifying
    @Transactional
    @Query("delete from ChooseDirection cd where cd.student.id=:sid")
    void removeBySid(@Param("sid") Integer sid);

}
