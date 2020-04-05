package com.example.backstage.repository;

import com.example.backstage.entity.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends BaseReporsitory<User, Integer>{
    @Query("from User u where u.number=:num")
    User findByNumber(@Param("num")Integer number);
}
