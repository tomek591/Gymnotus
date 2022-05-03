package com.example.gymnotus.service;

import com.example.gymnotus.entity.User;
import com.example.gymnotus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUserInDb(User user) {
        return userRepository.save(user);
    }
}
