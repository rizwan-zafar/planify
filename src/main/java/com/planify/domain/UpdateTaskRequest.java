package com.planify.domain;

import com.planify.domain.entity.TaskPriority;
import com.planify.domain.entity.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
