package com.example.gymnotus.entity;

import com.example.gymnotus.enums.ConditionType;

import javax.persistence.*;

@Entity
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conditionActivityId;
    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training conditionTraining;
    private ConditionType conditionType;
    private int time;
    private int distance;
    private String notes;

    public Condition() {

    }

    public Condition(ConditionType conditionType, int time, int distance, String notes) {
        this.conditionType = conditionType;
        this.time = time;
        this.distance = distance;
        this.notes = notes;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public double getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getId() {
        return conditionActivityId;
    }

    public void setId(Long id) {
        this.conditionActivityId = id;
    }

    public Training getTraining() {
        return conditionTraining;
    }

    public void setTraining(Training conditionTraining) {
        this.conditionTraining = conditionTraining;
    }
}
