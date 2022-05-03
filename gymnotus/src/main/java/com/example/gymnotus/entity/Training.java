package com.example.gymnotus.entity;

import com.example.gymnotus.enums.TrainingType;

import javax.persistence.*;
import java.util.*;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingId;
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;
    @OneToMany(mappedBy = "conditionTraining")
    private Set<Condition> conditionActivities = new HashSet<>();
    @OneToMany(mappedBy = "strengthTraining")
    private Set<Strength> strengthActivities = new HashSet<>();
    private Date date;
    private TrainingType type;

    public Training() {

    }

    public Training(Date date, TrainingType type) {
        this.date = date;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingId=" + trainingId +
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
