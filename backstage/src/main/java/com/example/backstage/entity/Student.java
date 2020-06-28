package com.example.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @MapsId
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private User user;
    private Float grade;

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
    @ManyToOne
    private Teacher teacher;


}
