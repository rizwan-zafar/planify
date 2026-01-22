package com.planify.domain.service.impl;

import com.planify.domain.dto.CreateProjectRequestDto;
import com.planify.domain.entity.Project;
import com.planify.domain.entity.ProjectStatus;
import com.planify.domain.entity.Task;
import com.planify.domain.service.ProjectService;
import com.planify.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.InstantSource;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project createProject(CreateProjectRequestDto request) {
        Instant now=Instant.now();
        Project project =new Project(null,null,request.name(), request.description(), request.startDate(),request.endDate(), ProjectStatus.OPEN,now,now);
        return projectRepository.save(project);
    }
}
