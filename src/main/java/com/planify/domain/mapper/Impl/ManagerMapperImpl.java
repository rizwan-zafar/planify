package com.planify.domain.mapper.Impl;

import com.planify.domain.dto.ManagerDto;
import com.planify.domain.entity.Manager;
import com.planify.domain.mapper.ManagerMapper;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapperImpl implements ManagerMapper {

    @Override
    public ManagerDto toDto(Manager manager) {
        return new ManagerDto(
                manager.getId(),
                manager.getName(),
                manager.getEmail(),
                manager.getDepartment(),
                manager.getGender()
                );
    }
}
