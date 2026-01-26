package com.planify.domain.dto;

import com.planify.domain.enums.ManagerGender;

public record UpdateManagerRequestDto(
        String name,
        String department,
        String email,
        ManagerGender gender
) {
}
