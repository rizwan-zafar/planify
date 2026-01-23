package com.planify.domain.service.impl;

import com.planify.domain.dto.CreateProjectRequestDto;
import com.planify.domain.dto.UpdateProjectRequestDto;
import com.planify.domain.entity.Project;
import com.planify.domain.entity.ProjectStatus;
import com.planify.domain.entity.Task;
import com.planify.domain.exception.ProjectNotFoundException;
import com.planify.domain.exception.TaskNotFoundException;
import com.planify.domain.service.ProjectService;
import com.planify.repository.ProjectRepository;
import com.planify.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.InstantSource;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Project createProject(CreateProjectRequestDto request) {
        Instant now=Instant.now();
        if (!taskRepository.existsById(request.task())) {
            throw new TaskNotFoundException(request.task());
        }
        Project project =new Project(null,request.task(),request.name(), request.description(), request.startDate(),request.endDate(), ProjectStatus.OPEN,now,now);
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(UUID id, UpdateProjectRequestDto request) {
      Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectNotFoundException(id));
        project.setName(request.name());
        project.setDescription(request.description());
        project.setTask(request.task());
        project.setStartDate(request.startDate());
        project.setEndDate(request.endDate());
        project.setStatus(request.status());
        return  projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll(Sort.by(Sort.Direction.ASC,"created"));
    }

    @Override
    public void deleteProject(UUID id) {
        Project project=projectRepository.findById(id).orElseThrow(()-> new ProjectNotFoundException(id));
        if(project!=null)
        {
            projectRepository.deleteById(id);
        }
    }
}
