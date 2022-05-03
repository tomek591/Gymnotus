package com.example.gymnotus.controller;

import com.example.gymnotus.entity.User;
import com.example.gymnotus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-user-test")
    public User getUserFromRequest(@RequestBody User user) {
        return userService.saveUserInDb(user);
    }
}
