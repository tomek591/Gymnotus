package com.example.gymnotus.model;

import com.example.gymnotus.enums.GenderType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private GenderType genderType;
    private Date birthDate;
    private Integer height;
    private Integer weight;
    private LocalDateTime created;
    @OneToMany
    @JoinColumn(name = "userId", updatable = false, insertable = false)
    private List<Training> trainings;
}
