package com.example.backstage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String course_name;
    private String extra;

    @OneToMany( mappedBy = "course")
    private List<Elective> electiveList;
}
