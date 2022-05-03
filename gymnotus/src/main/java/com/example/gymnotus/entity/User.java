package com.example.gymnotus.entity;

import com.example.gymnotus.enums.GenderType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "user")
    private Set<Training> userTrainings = new HashSet<>();
    private String username;
    private double height;
    private double weight;
    private GenderType gender;
    private Date birthDate;


    public User() {

    }

    public User(String username, double height, double weight, GenderType gender, Date birthDate) {
        this.username = username;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Training> getUserTrainings() {
        return userTrainings;
    }

    public void setUserTrainings(Set<Training> userTrainings) {
        this.userTrainings = userTrainings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
