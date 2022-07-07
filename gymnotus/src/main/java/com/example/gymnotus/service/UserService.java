package com.example.gymnotus.service;

import com.example.gymnotus.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.gymnotus.repository.UserRepository;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User editUser(Long id, User user) {
            User userFromDb  = userRepository.findById(id)
                    .orElseThrow();
            if(user.getGenderType() != null) {
                userFromDb.setGenderType(user.getGenderType());
            }
            if(user.getBirthDate() != null) {
                userFromDb.setBirthDate(user.getBirthDate());
            }
            if(user.getHeight() != null) {
                userFromDb.setHeight(user.getHeight());
            }
            if(user.getWeight() != null) {
                userFromDb.setWeight(user.getWeight());
            }
            return userFromDb;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
