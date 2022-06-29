package com.example.gymnotus.exception;

import com.example.gymnotus.controller.TrainingController;
import com.example.gymnotus.controller.UserController;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@ControllerAdvice(assignableTypes = {UserController.class, TrainingController.class})
public class DefaultControllerAdvice {

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<String> handleNoSuchElementException() {
        return new ResponseEntity<>("Value is not present in DB, please change your request.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<String> handleEmptyResultDataAccessException() {
        return new ResponseEntity<>("Value is not present in DB, please change your request.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException() {
        return new ResponseEntity<>("Wrong input type, please change your request.", HttpStatus.BAD_REQUEST);
    }
}
