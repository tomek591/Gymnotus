package com.example.gymnotus.model;

import com.example.gymnotus.enums.TrainingType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private TrainingType trainingType;
    private Date date;
    @OneToMany
    @JoinColumn(name = "trainingId", updatable = false, insertable = false)
    private List<Strength> strengthTrainings;
    @OneToMany
    @JoinColumn(name = "trainingId", updatable = false, insertable = false)
    private List<Endurance> enduranceTrainings;
}
