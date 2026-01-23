package com.planify.domain.dto;

import com.planify.domain.enums.TaskPriority;
import com.planify.domain.enums.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status

) {
}
