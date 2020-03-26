package com.example.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Elective {
    @Id
    @GeneratedValue
    @Column(length = 16)
    private UUID uuid;

    private String extra;
    private Float grade;
    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Course course;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Student student;
}
