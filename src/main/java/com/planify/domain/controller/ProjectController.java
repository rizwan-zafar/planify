package com.planify.domain.controller;

import com.planify.domain.dto.CreateProjectRequestDto;
import com.planify.domain.dto.ProjectDto;
import com.planify.domain.entity.Project;
import com.planify.domain.mapper.ProjectMapper;
import com.planify.domain.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectMapper projectMapper;

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody CreateProjectRequestDto createProjectRequestDto)
    {
        Project project=projectService.createProject(createProjectRequestDto);
        return new ResponseEntity<>( projectMapper.toDto(project),HttpStatus.CREATED);
    }


}
