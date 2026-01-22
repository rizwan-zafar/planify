package com.planify.domain.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateProjectRequestDto(
        @NotBlank(message = ERROR_MESSAGE_NAME_LENGTH)
        @Length(max = 255,message = ERROR_MESSAGE_NAME_LENGTH)
        String name,

        @NotBlank(message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
        @Length(message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
        String description,
        LocalDate startDate,
        LocalDate endDate

) {
    private static final String ERROR_MESSAGE_NAME_LENGTH="Name must be between 1 to 255 characters";
    private static final String ERROR_MESSAGE_DESCRIPTION_LENGTH="Descritpion must be less then 1000 characters";
}