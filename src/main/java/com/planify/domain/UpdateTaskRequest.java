package com.planify.domain;

import com.planify.domain.enums.TaskPriority;
import com.planify.domain.enums.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
