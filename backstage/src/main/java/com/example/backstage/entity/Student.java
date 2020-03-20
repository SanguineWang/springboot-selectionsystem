package com.example.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
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


    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;
//    @ToString.Exclude
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Elective> electiveList;
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<ChooseDirection> chooseDirections;
    @ManyToOne
    private Teacher teacher;


}
