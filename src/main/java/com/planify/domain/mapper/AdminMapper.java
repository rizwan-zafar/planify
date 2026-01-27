package com.planify.domain.mapper;

import com.planify.domain.dto.AdminDto;
import com.planify.domain.entity.Admin;

public interface AdminMapper {
    AdminDto toDto(Admin admin);
}
