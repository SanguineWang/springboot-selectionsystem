package com.example.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Elective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String extra;
    private float grade;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Course course;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Student student;
}
