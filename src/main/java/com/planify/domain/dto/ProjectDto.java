package com.planify.domain.dto;

import com.planify.domain.entity.ProjectStatus;

import java.time.LocalDate;
import java.util.UUID;

public record ProjectDto(
        UUID id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        ProjectStatus status

) {

}
//Response
// id
// name
// description
// startDate
// endDate
// status