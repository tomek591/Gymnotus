package com.example.gymnotus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Strength {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long trainingId;
    private String name;
    private int reps;
    private int series;
    private int weight;
    private int timeInSeconds;
    private String shortNotes;
}
