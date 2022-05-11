package com.example.gymnotus.controller;

import com.example.gymnotus.model.User;
import com.example.gymnotus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/users")
    public User editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@RequestBody long id) {
        userService.deleteUser(id);
    }
}
