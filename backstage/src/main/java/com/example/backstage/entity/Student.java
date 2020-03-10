package com.example.backstage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,length = 8)
    private int student_number;
    private String key="123456";
    private String name;
    private String extra;
    private Float grade;

    @OneToMany(mappedBy = "student")
    private List<Elective> electiveList;

    @ManyToOne
    private Teacher teacher;
}
