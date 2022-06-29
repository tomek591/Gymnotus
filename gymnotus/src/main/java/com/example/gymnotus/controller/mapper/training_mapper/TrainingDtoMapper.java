package com.example.gymnotus.controller.mapper.training_mapper;

import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoRequest;
import com.example.gymnotus.model.Training;

public class TrainingDtoMapper {

    public static Training mapTrainingDtoToTraining(TrainingDtoRequest trainingDtoRequest) {
        Training training = new Training();
        training.setUserId(trainingDtoRequest.userId());
        training.setTrainingType(trainingDtoRequest.trainingType());
        training.setName(trainingDtoRequest.name());
        training.setDate(trainingDtoRequest.date());
        return training;
    }
}
