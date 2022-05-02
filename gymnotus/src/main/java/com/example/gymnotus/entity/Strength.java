package com.example.gymnotus.entity;

import javax.persistence.*;

@Entity
@Table(name = "STRENGTH")
public class Strength{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strengthActivityId;
    @ManyToOne
    private Training training;
    private int reps;
    private int series;
    private int weight;
    private int seconds;
    private String notes;

    public Strength() {

    }

    public Strength(int reps, int series, int weight, int seconds) {
        this.reps = reps;
        this.series = series;
        this.weight = weight;
        this.seconds = seconds;
    }

    public Long getStrengthActivityId() {
        return strengthActivityId;
    }

    public void setStrengthActivityId(Long strengthActivityId) {
        this.strengthActivityId = strengthActivityId;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
