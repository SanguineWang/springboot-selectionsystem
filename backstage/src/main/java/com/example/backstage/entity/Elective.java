package com.example.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String extra;
    private Float grade;

    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Course course;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Student student;
}
