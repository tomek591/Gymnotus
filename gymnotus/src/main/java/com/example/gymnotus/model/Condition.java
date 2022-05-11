package com.example.gymnotus.model;

import com.example.gymnotus.enums.ConditionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "cond")
public class Condition extends Exercise {
    private ConditionType conditionType;
    private int distanceInMeters;
}
