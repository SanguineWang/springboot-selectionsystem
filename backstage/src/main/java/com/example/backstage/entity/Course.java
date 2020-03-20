package com.example.backstage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
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
    private Float weight;
    private Float lowest;

    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;

    @ToString.Exclude
    @OneToMany( mappedBy = "course")
    private List<Elective> electiveList;

    @ManyToOne
    private Teacher teacher;
}
