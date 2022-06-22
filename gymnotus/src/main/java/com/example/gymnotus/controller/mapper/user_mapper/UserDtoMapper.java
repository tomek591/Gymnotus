package com.example.gymnotus.controller.mapper.user_mapper;

import com.example.gymnotus.controller.dto.user_dtos.UserDtoRequest;
import com.example.gymnotus.controller.dto.user_dtos.UserDtoRequestPut;
import com.example.gymnotus.controller.dto.user_dtos.UserDtoResponse;
import com.example.gymnotus.model.User;

public class UserDtoMapper {

    public static UserDtoResponse mapUserToResponseDto(User user) {
        return new UserDtoResponse(
                user.getId(),
                user.getUsername(),
                user.getGenderType(),
                user.getBirthDate(),
                user.getHeight(),
                user.getWeight(),
                user.getCreated());
    }

    public static User mapRequestDtoToUser(UserDtoRequest userDtoRequest) {
        User user = new User();
        user.setUsername(userDtoRequest.username());
        user.setGenderType(userDtoRequest.genderType());
        user.setBirthDate(userDtoRequest.birthDate());
        user.setHeight(userDtoRequest.height());
        user.setWeight(userDtoRequest.weight());
        user.setCreated(userDtoRequest.created());
        return user;
    }

    public static User mapPutRequestDtoToUser(UserDtoRequestPut userDtoRequestPut) {
        User user = new User();
        user.setGenderType(userDtoRequestPut.genderType());
        user.setBirthDate(userDtoRequestPut.birthDate());
        user.setHeight(userDtoRequestPut.height());
        user.setWeight(userDtoRequestPut.weight());
        return user;
    }
}
