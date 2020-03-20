package com.example.backstage.repository;

import com.example.backstage.entity.Direction;
import com.example.backstage.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends BaseReporsitory<Teacher, Integer> {

}
