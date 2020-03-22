package com.example.backstage.entity;

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
public class Direction {

    @Id
    @GeneratedValue
    @Column(length = 16)
    private UUID uuid;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;
    private Float weight;

    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;
    @ManyToOne
    private Teacher teacher;

    @ToString.Exclude
    @OneToMany( mappedBy = "direction")
    private List<ChooseDirection> chooseDirections;


}
