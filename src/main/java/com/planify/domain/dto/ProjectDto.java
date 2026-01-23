package com.planify.domain.dto;

import com.planify.domain.entity.ProjectStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectDto(
        UUID id,
        String name,
        String description,
        TaskDto task,
        LocalDate startDate,
        LocalDate endDate,
        ProjectStatus status
) {}