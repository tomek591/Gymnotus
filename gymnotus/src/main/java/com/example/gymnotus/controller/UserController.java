package com.example.gymnotus.controller;

import com.example.gymnotus.controller.dto.UserDtoRequest;
import com.example.gymnotus.controller.dto.UserDtoResponse;
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
    public UserDtoResponse getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        return new UserDtoResponse(user.getId(),
                user.getUsername(),
                user.getGenderType(),
                user.getBirthDate(),
                user.getHeight(),
                user.getWeight(),
                user.getCreated());
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody UserDtoRequest userDtoRequest) {
        User user = new User();
        user.setUsername(userDtoRequest.username());
        user.setGenderType(userDtoRequest.genderType());
        user.setBirthDate(userDtoRequest.birthDate());
        user.setHeight(userDtoRequest.height());
        user.setWeight(userDtoRequest.weight());
        user.setCreated(userDtoRequest.created());
        return userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    public User editUser(@PathVariable Long id, @Valid @RequestBody UserDtoRequest userDtoRequest) {
        User user = new User();
        user.setGenderType(userDtoRequest.genderType());
        user.setBirthDate(userDtoRequest.birthDate());
        user.setHeight(userDtoRequest.height());
        user.setWeight(userDtoRequest.weight());
        return userService.editUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
