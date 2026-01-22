package com.planify.domain.dto;

import java.time.Instant;

public record ErrorDto(
        Instant timestamp,
        int status,
        String error,
        String path,
        String errorMessage
){
}
