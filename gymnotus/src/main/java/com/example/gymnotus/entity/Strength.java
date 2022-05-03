package com.example.gymnotus.entity;

import javax.persistence.*;

@Entity
public class Strength{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long strengthActivityId;
    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training strengthTraining;
    private int reps;
    private int series;
    private int weight;
    private int seconds;
    private String notes;

    public Strength() {

    }

    public Strength(int reps, int series, int weight, int seconds, String notes) {
        this.reps = reps;
        this.series = series;
        this.weight = weight;
        this.seconds = seconds;
        this.notes = notes;
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
        return strengthTraining;
    }

    public void setTraining(Training strengthTraining) {
        this.strengthTraining = strengthTraining;
    }
}
