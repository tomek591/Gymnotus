package com.example.gymnotus.model;

import com.example.gymnotus.enums.ConditionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long trainingId;
    private ConditionType conditionType;
    private int distanceInMeters;
    private int timeInSeconds;
    private String shortNotes;
}
