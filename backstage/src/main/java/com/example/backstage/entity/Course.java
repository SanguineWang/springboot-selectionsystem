package com.example.backstage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String extra;
    private float weight;
    private float lowest;
    @ToString.Exclude
    @OneToMany( mappedBy = "course")
    private List<Elective> electiveList;

    @ManyToOne
    private Teacher teacher;
}
