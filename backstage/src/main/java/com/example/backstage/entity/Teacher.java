package com.example.backstage.entity;

import ch.qos.logback.classic.turbo.TurboFilter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    private Integer upper_limit; //学生上限
    private Float mark_limit;//分数下限
    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;
    //学生上限
    private Boolean isAdmin;//是否管理员

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Student> studentList;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Course> courseList;

    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Direction> directionList;
}
