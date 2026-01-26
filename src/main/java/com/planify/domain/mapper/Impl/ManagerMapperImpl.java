package com.planify.domain.mapper.Impl;

import com.planify.domain.dto.ManagerDto;
import com.planify.domain.dto.ProjectDto;
import com.planify.domain.entity.Manager;
import com.planify.domain.mapper.ManagerMapper;
import com.planify.domain.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapperImpl implements ManagerMapper {

    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public ManagerDto toDto(Manager manager) {
        return new ManagerDto(
                manager.getId(),
                manager.getName(),
                manager.getEmail(),
                manager.getDepartment(),
                manager.getGender(),
                manager.getProjects().stream().map(project ->projectMapper.toDto(project)).toList()
        );
    }
}
