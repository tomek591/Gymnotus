package com.example.gymnotus.controller.dto;

import com.example.gymnotus.enums.GenderType;

import java.util.Date;

public record UserDtoRequestForPut(GenderType genderType,
                                   Date birthDate,
                                   Double height,
                                   Double weight) {
}
