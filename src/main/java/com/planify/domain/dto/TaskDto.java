package com.planify.domain.dto;

import com.planify.domain.entity.TaskPriority;
import com.planify.domain.entity.TaskStatus;

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
