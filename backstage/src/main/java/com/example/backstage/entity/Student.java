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
    @Column(length = 10)
    private int id;
    @Column(length = 16)
    private String password ="123456";
    private String name;
    private String extra;
    private Float grade;

    private Boolean isSelected;
    @OneToMany(mappedBy = "student")
    private List<Elective> electiveList;

    @ManyToOne
    private Teacher teacher;
}
