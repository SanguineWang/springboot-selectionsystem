package com.example.backstage.entity;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue
    @Column(length = 16)
    private UUID uuid;

    @Column(length = 10,unique = true)
    private Integer id;
    @Column(length = 16)
    @JsonIgnore
    private String password = "123456";
    private String name;
    private String extra;
    private Integer upper_limit; //学生上限
    private Float mark_limit;//分数下限
    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;
    //学生上限
    private Boolean isAdmin;//是否管理员

    @JsonIgnore
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Student> studentList;
    @JsonIgnore
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Course> courseList;
    @JsonIgnore
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
    private List<Direction> directionList;
}
