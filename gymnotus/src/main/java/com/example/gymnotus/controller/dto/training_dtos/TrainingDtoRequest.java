package com.example.gymnotus.controller.dto.training_dtos;

import com.example.gymnotus.enums.TrainingType;

import javax.validation.constraints.NotNull;
import java.util.Date;

public record TrainingDtoRequest(
        @NotNull Long userId,
        String name,
        @NotNull TrainingType trainingType,
        Date date) {
}
