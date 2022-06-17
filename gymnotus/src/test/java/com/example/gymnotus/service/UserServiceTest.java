package com.example.gymnotus.service;

import com.example.gymnotus.enums.GenderType;
import com.example.gymnotus.model.User;
import com.example.gymnotus.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUser_correctId_shouldGetUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setGenderType(GenderType.MALE);
        user.setHeight(180.0);
        user.setWeight(100.0);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = userService.getUser(1L);
        Assertions.assertEquals(user, result);
        Assertions.assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    void getUser_existingId_optionalShouldThrowException() throws ParseException {
        User user = new User();
        user.setUsername("testuser");
        user.setGenderType(GenderType.MALE);
        user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        user.setHeight(180.0);
        user.setWeight(100.0);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> userService.getUser(1L));
    }

    @Test
    void addUser_correctCase_shouldAdd() throws ParseException {
        User user = new User();
        user.setUsername("testuser");
        user.setGenderType(GenderType.MALE);
        user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2017-09-09"));
        user.setHeight(180.0);
        user.setWeight(100.0);

        when(userRepository.save(user)).thenReturn(user);
        userService.addUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser_correctCase_shouldUpdate() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setGenderType(GenderType.MALE);
        user.setHeight(180.0);
        user.setWeight(100.0);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.editUser(1L, user);
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void updateUser_userNotExists_shouldNotUpdateAndThrowException() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setGenderType(GenderType.MALE);
        user.setHeight(180.0);
        user.setWeight(100.0);

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> userService.getUser(1L));
    }

    @Test
    void deleteUser_correctCase_shouldDelete() {
        User user = new User();
        user.setId(1L);
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }
}