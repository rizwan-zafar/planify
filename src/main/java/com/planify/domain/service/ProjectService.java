package com.planify.domain.service;

import com.planify.domain.dto.CreateProjectRequestDto;
import com.planify.domain.dto.UpdateProjectRequestDto;
import com.planify.domain.entity.Project;

import java.util.List;
import java.util.UUID;


public interface ProjectService {

    Project createProject(CreateProjectRequestDto request);
    Project updateProject(UUID id, UpdateProjectRequestDto request);
    List<Project> getAllProjects();
    void deleteProject(UUID id);
}
