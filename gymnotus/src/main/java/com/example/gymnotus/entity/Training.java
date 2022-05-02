package com.example.gymnotus.entity;

import com.example.gymnotus.enums.TrainingType;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "TRAINING")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingId;
    private Long userId;
    @OneToMany(mappedBy = "training")
    private Set<Condition> conditionActivities = new HashSet<>();
    @OneToMany(mappedBy = "training")
    private Set<Strength> strengthActivities = new HashSet<>();
    private Date date;
    private TrainingType type;

    public Training() {

    }

    public Training(Long userId, Date date, TrainingType type) {
        this.userId = userId;
        this.date = date;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingId=" + trainingId +
                ", userId=" + userId +
                ", conditionActivities=" + conditionActivities +
                ", strengthActivities=" + strengthActivities +
                ", date=" + date +
                ", type=" + type +
                '}';
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Condition> getConditionActivities() {
        return conditionActivities;
    }

    public void setConditionActivities(Set<Condition> conditionActivities) {
        this.conditionActivities = conditionActivities;
    }

    public Set<Strength> getStrengthActivities() {
        return strengthActivities;
    }

    public void setStrengthActivities(Set<Strength> strengthActivities) {
        this.strengthActivities = strengthActivities;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }
}
