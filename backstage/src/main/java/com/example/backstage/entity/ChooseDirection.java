package com.example.backstage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class ChooseDirection {
    @Id
    @GeneratedValue
    @Column(length = 16)
    private UUID uuid;

    @Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
    private LocalDate insertTime;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Direction direction;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    private Student student;
}
