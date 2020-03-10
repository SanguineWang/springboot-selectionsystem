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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true ,length = 8)
    private int teacher_number;

    private String key = "123456";
    private String name;
    private String extra;

    //学生上限
    private int upper_limit;

    @OneToMany(mappedBy = "teacher")
    private List<Student> studentList;
}
