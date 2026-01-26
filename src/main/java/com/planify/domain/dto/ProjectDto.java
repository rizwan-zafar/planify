package com.planify.domain.dto;

import com.planify.domain.enums.ProjectStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProjectDto(
        UUID id,
        String name,
        String description,
        List<TaskDto> task,
        LocalDate startDate,
        LocalDate endDate,
        ProjectStatus status
) {}