package com.example.gymnotus.service;

import com.example.gymnotus.enums.TrainingType;
import com.example.gymnotus.exception.training.UserWithThisIdNotExistsException;
import com.example.gymnotus.model.Training;
import com.example.gymnotus.repository.TrainingRepository;
import com.example.gymnotus.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TrainingService trainingService;

    @Test
    void addTraining_correctCase_shouldAdd() throws ParseException, UserWithThisIdNotExistsException {
        Training newTraining = new Training();
        newTraining.setUserId(10L);
        newTraining.setTrainingType(TrainingType.STRENGTH);
        newTraining.setName("FBW");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));

        when(userRepository.existsById(newTraining.getUserId())).thenReturn(true);
        trainingService.addTraining(newTraining);
        verify(userRepository, times(1)).existsById(newTraining.getUserId());
        verify(trainingRepository, times(1)).save(newTraining);
    }

    @Test
    void addTraining_caseWhenUserNotExist_shouldAdd() throws ParseException {
        Training newTraining = new Training();
        newTraining.setUserId(10L);
        newTraining.setTrainingType(TrainingType.STRENGTH);
        newTraining.setName("FBW");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));

        when(userRepository.existsById(newTraining.getUserId())).thenReturn(false);
        Assertions.assertThrows(UserWithThisIdNotExistsException.class,
                () -> trainingService.addTraining(newTraining));
        verify(userRepository, times(1)).existsById(newTraining.getUserId());
        verify(trainingRepository, times(0)).save(newTraining);
    }

    @Test
    void updateTraining_correctCase_shouldUpdate() throws ParseException {
        Training newTraining = new Training();
        newTraining.setId(1L);
        newTraining.setUserId(10L);
        newTraining.setTrainingType(TrainingType.STRENGTH);
        newTraining.setName("FBW A");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));

        when(trainingRepository.findById(newTraining.getId())).thenReturn(Optional.of(newTraining));
        trainingService.editTraining(1L, newTraining);
        verify(trainingRepository, times(1)).findById(newTraining.getId());
    }

    @Test
    void updateTraining_trainingNotExists_shouldNotUpdateAndThrowException() throws ParseException {
        Training newTraining = new Training();
        newTraining.setId(1L);
        newTraining.setUserId(10L);
        newTraining.setTrainingType(TrainingType.STRENGTH);
        newTraining.setName("FBW A");
        newTraining.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));

        when(trainingRepository.findById(newTraining.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> trainingService.editTraining(1L, newTraining));
    }

    @Test
    void deleteTraining_correctCase_shouldDelete() {
        Training training = new Training();
        training.setId(1L);
        trainingService.deleteTraining(training.getId());
        verify(trainingRepository, times(1)).deleteById(training.getId());
    }
}
