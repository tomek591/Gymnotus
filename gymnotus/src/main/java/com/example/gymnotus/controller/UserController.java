package com.example.gymnotus.controller;

import com.example.gymnotus.controller.dto.user_dtos.UserDtoRequest;
import com.example.gymnotus.controller.dto.user_dtos.UserDtoPutRequest;
import com.example.gymnotus.controller.dto.user_dtos.UserDtoResponse;
import com.example.gymnotus.controller.mapper.user_mapper.UserDtoMapper;
import com.example.gymnotus.model.User;
import com.example.gymnotus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public UserDtoResponse getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return UserDtoMapper.mapUserToResponseDto(user);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoResponse addUser(@Valid @RequestBody UserDtoRequest userDtoRequest) {
        User user = UserDtoMapper.mapRequestDtoToUser(userDtoRequest);
        User userFromDb = userService.addUser(user);
        return UserDtoMapper.mapUserToResponseDto(userFromDb);
    }

    @PutMapping("/users/{id}")
    public UserDtoResponse editUser(@PathVariable Long id, @RequestBody UserDtoPutRequest userDtoRequestPut) {
        User user = UserDtoMapper.mapPutRequestDtoToUser(userDtoRequestPut);
        User userFromDb = userService.editUser(id, user);
        return UserDtoMapper.mapUserToResponseDto(userFromDb);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
