package com.example.gymnotus.exception.training;

import com.example.gymnotus.controller.TrainingController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = TrainingController.class)
public class TrainingControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException() {
        return new ResponseEntity<>("UserId cannot be null and has to be long int, trainingType cannot be null and has to be \"STRENGTH\" or \"ENDURANCE\", please change your request.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserWithThisIdNotExistsException.class)
    public ResponseEntity<String> handleUserWithThisIdNotExistException() {
        return new ResponseEntity<>("User with this id does not exists, please change your request.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException() {
        return new ResponseEntity<>("UserId has to be long int, trainingType has to be \"STRENGTH\" or \"ENDURANCE\", please change your request", HttpStatus.BAD_REQUEST);
    }
}
