package com.example.gymnotus.controller.dto.user_dtos;

import com.example.gymnotus.enums.GenderType;

import java.time.LocalDateTime;
import java.util.Date;

public record UserDtoResponse(long id,
                              String username,
                              GenderType genderType,
                              Date birthDate,
                              Double height,
                              Double weight,
                              LocalDateTime created) {
}
