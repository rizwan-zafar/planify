package com.planify.domain.mapper;

import com.planify.domain.dto.ManagerDto;
import com.planify.domain.entity.Manager;

public interface ManagerMapper {
    ManagerDto toDto(Manager manager);
}
