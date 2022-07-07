package com.example.gymnotus.controller.dto.training_dtos;

import com.example.gymnotus.enums.TrainingType;

import java.util.Date;

public record TrainingDtoResponse(Long id,
                                  Long userId,
                                  String name,
                                  TrainingType trainingType,
                                  Date date) {
}
