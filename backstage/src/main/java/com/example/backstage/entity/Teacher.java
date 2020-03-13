package com.example.backstage.entity;

import ch.qos.logback.classic.turbo.TurboFilter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Teacher {
    @Id
    @Column(length = 10)
    private int id;
    @Column(length = 16)
    private String password = "123456";
    private String name;
    private String extra;

    //学生上限
    private int upper_limit;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Student> studentList;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Course> courseList;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Direction> directionList;
}
