package com.planify.domain.dto;

import com.planify.domain.entity.ProjectStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public record UpdateProjectRequestDto(

        @NotBlank(message=ERROR_MESSAGE_NAME_LENGTH)
        @Length(max = 255,message = ERROR_MESSAGE_NAME_LENGTH)
        String name,
        @NotNull(message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
        @Length( min = 1,max = 1000,message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
        String description,

        @NotNull(message = ERROR_MESSAGE_START_DATE_FUTURE)
        @FutureOrPresent(message = ERROR_MESSAGE_START_DATE_FUTURE)
        LocalDate startDate,

        @NotNull(message = ERROR_MESSAGE_END_DATE_FUTURE)
        @FutureOrPresent(message = ERROR_MESSAGE_END_DATE_FUTURE)
        LocalDate endDate,

        @NotNull(message = ERROR_MESSAGE_TASK_STATUS)
        ProjectStatus status
) {
    private static final String ERROR_MESSAGE_NAME_LENGTH="Title must be between 1 to 255 characters";
    private static final String ERROR_MESSAGE_DESCRIPTION_LENGTH="Description must be less then 1000 characters";
    private static final String ERROR_MESSAGE_START_DATE_FUTURE="Start date must be in future";
    private static final String ERROR_MESSAGE_END_DATE_FUTURE="End date must be in future";
    private static final String ERROR_MESSAGE_TASK_STATUS="Task Status must be provided";
}
