package com.example.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    @Column(length = 16)
    private UUID uuid;

    @Column(length = 10,unique = true)
    private Integer id;

    private String name;
    private String extra;
    private Float grade;
    private Boolean isSelected;
    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Elective> electiveList;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<ChooseDirection> chooseDirections;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    private Teacher teacher;


}
