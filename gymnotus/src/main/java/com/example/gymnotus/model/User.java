package com.example.gymnotus.model;

import com.example.gymnotus.enums.GenderType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private GenderType genderType;
    private Date birthDate;
    private Double height;
    private Double weight;
    private LocalDateTime created;
    @OneToMany
    @JoinColumn(name = "userId", updatable = false, insertable = false)
    private List<Training> trainings;
}
