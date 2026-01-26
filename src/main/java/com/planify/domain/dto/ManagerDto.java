package com.planify.domain.dto;

import com.planify.domain.entity.Project;
import com.planify.domain.enums.ManagerGender;

import java.util.List;
import java.util.UUID;

public record ManagerDto(
        UUID id,
        String name,
        String email,
        String department,
        ManagerGender gender,
        List<ProjectDto> projects
) {}
