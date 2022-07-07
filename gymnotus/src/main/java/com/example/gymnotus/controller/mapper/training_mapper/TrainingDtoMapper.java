package com.example.gymnotus.controller.mapper.training_mapper;

import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoPutRequest;
import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoRequest;
import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoResponse;
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

    public static Training mapTrainingPutDtoToTraining(TrainingDtoPutRequest trainingDtoPutRequest) {
        Training training = new Training();
        training.setName(trainingDtoPutRequest.name());
        training.setDate(trainingDtoPutRequest.date());
        return training;
    }

    public static TrainingDtoResponse mapTrainingToTrainingDto(Training training) {
        return new TrainingDtoResponse(
                training.getId(),
                training.getUserId(),
                training.getName(),
                training.getTrainingType(),
                training.getDate()
        );
    }
}
