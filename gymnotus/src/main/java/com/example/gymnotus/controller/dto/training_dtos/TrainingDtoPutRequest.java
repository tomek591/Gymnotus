package com.example.gymnotus.controller.dto.training_dtos;

import java.util.Date;

public record TrainingDtoPutRequest(
        String name,
        Date date) {
}
