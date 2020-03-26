package com.example.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Course {

    //    @Id
//    @GeneratedValue
//    @Column(length = 16)
//    private UUID uuid;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;
    private String extra;
    private Float weight;
    private Float lowest;

    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Elective> electiveList;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Teacher teacher;
}
