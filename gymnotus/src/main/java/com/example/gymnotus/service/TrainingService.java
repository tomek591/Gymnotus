package com.example.gymnotus.service;

import com.example.gymnotus.exception.training.UserWithThisIdNotExistsException;
import com.example.gymnotus.model.Training;
import com.example.gymnotus.repository.TrainingRepository;
import com.example.gymnotus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;

    private final UserRepository userRepository;

    public Training addTraining(Training training) throws UserWithThisIdNotExistsException {

        if(userRepository.existsById(training.getUserId())) {
            return trainingRepository.save(training);
        }
        throw new UserWithThisIdNotExistsException();
    }

    @Transactional
    public Training editTraining(Long id, Training training) {
        Training trainingFromDb = trainingRepository.findById(id)
                        .orElseThrow();
        if(training.getName() != null) {
            trainingFromDb.setName(training.getName());
        }
        if(training.getDate() != null) {
            trainingFromDb.setDate(training.getDate());
        }
        return trainingFromDb;
    }
}
