package com.example.gymnotus.controller.dto.user_dtos;

import com.example.gymnotus.enums.GenderType;

import java.util.Date;

public record UserDtoRequestPut(GenderType genderType,
                                Date birthDate,
                                Double height,
                                Double weight) {
}
