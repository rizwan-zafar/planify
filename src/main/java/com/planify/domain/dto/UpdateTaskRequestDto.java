package com.planify.domain.dto;

import com.planify.domain.enums.TaskPriority;
import com.planify.domain.enums.TaskStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record UpdateTaskRequestDto(
        @NotBlank(message = ERROR_MESSAGE_TITLE_LENGTH)
        @Length(max = 255,message = ERROR_MESSAGE_TITLE_LENGTH)
        String title,

        @Length(max = 1000, message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
            @Nullable
        String description,

        @FutureOrPresent(message = ERROR_MESSAGE_DUE_DATE_FUTURE)
        LocalDate dueDate,

        @NotNull(message = ERROR_MESSAGE_TASK_PRIORITY)
        TaskPriority priority,

        @NotNull(message = ERROR_MESSAGE_TASK_PRIORITY)
        TaskStatus status
) {
    private static final String ERROR_MESSAGE_TITLE_LENGTH="Title must be between 1 to 255 characters";
    private static final String ERROR_MESSAGE_DESCRIPTION_LENGTH="Title must be less then 1000 characters";
    private static final String ERROR_MESSAGE_DUE_DATE_FUTURE="Due date must be in future";
    private static final String ERROR_MESSAGE_TASK_PRIORITY="Task Priority must be provided";
    private static final String ERROR_MESSAGE_TASK_STATUS="Task Status must be provided";

}
