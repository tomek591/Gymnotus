package com.example.gymnotus.controller.dto.user_dtos;

import com.example.gymnotus.enums.GenderType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

public record UserDtoRequest(
        @NotNull @Size(min = 6, max=25) String username,
        GenderType genderType,
        Date birthDate,
        Double height,
        Double weight,
        LocalDateTime created) {
}
