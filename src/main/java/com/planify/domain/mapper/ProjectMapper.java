package com.planify.domain.mapper;

import com.planify.domain.dto.ProjectDto;
import com.planify.domain.entity.Project;

public interface ProjectMapper {
    ProjectDto toDto(Project project);
}
