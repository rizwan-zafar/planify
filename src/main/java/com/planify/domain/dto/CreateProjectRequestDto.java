package com.planify.domain.dto;

import java.time.LocalDate;

public record CreateProjectRequestDto(
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate

) {}