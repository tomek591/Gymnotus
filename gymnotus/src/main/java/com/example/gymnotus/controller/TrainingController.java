package com.example.gymnotus.controller;

import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoPutRequest;
import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoRequest;
import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoResponse;
import com.example.gymnotus.controller.mapper.training_mapper.TrainingDtoMapper;
import com.example.gymnotus.exception.training.UserWithThisIdNotExistsException;
import com.example.gymnotus.model.Training;
import com.example.gymnotus.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping("/trainings")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDtoResponse addTraining(@Valid @RequestBody TrainingDtoRequest trainingDtoRequest) throws UserWithThisIdNotExistsException {
        Training training = TrainingDtoMapper.mapTrainingDtoToTraining(trainingDtoRequest);
        Training trainingFromDb = trainingService.addTraining(training);
        return TrainingDtoMapper.mapTrainingToTrainingDto(trainingFromDb);
    }

    @PutMapping("/trainings/{id}")
    public TrainingDtoResponse editTraining(@Valid @RequestBody TrainingDtoPutRequest trainingDtoPutRequest, @PathVariable long id) {
        Training training = TrainingDtoMapper.mapTrainingPutDtoToTraining(trainingDtoPutRequest);
        Training trainingFromDb = trainingService.editTraining(id, training);
        return TrainingDtoMapper.mapTrainingToTrainingDto(trainingFromDb);
    }

}
