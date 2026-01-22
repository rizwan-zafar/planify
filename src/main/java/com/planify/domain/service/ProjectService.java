package com.planify.domain.service;

import com.planify.domain.dto.CreateProjectRequestDto;
import com.planify.domain.entity.Project;


public interface ProjectService {

    Project createProject(CreateProjectRequestDto request);
}
