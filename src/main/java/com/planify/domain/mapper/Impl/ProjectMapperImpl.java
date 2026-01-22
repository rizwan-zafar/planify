package com.planify.domain.mapper.Impl;

import com.planify.domain.dto.ProjectDto;
import com.planify.domain.entity.Project;
import com.planify.domain.mapper.ProjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapperImpl implements ProjectMapper {
    @Override
    public ProjectDto toDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus());
    }
}
