package com.example.gymnotus.controller;

import com.example.gymnotus.controller.dto.training_dtos.TrainingDtoRequest;
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
    public Training addTraining(@Valid @RequestBody TrainingDtoRequest trainingDtoRequest) throws UserWithThisIdNotExistsException {
        Training training = TrainingDtoMapper.mapTrainingDtoToTraining(trainingDtoRequest);
        return trainingService.addTraining(training);
    }

}
