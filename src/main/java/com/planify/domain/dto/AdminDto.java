package com.planify.domain.dto;

import com.planify.domain.enums.AdminRole;

public record AdminDto(
String name,
String email,
AdminRole role
)
{
}
