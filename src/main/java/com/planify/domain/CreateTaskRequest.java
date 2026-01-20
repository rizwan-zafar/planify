package com.planify.domain;

import com.planify.domain.entity.TaskPriority;

import java.time.LocalDate;

public record CreateTaskRequest(
    String title,
    String description,
    LocalDate dueDate,
    TaskPriority priority

) { }
