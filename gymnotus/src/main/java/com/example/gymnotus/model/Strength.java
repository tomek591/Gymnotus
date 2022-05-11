package com.example.gymnotus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Strength extends Exercise {
    private String name;
    private int reps;
    private int series;
    private int weight;
}
