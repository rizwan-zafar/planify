package com.planify.domain.controller;

import com.planify.domain.dto.*;
import com.planify.domain.entity.Project;
import com.planify.domain.mapper.ProjectMapper;
import com.planify.domain.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(@RequestParam UUID id, @Valid @RequestBody UpdateProjectRequestDto updateProjectRequestDto)
    {
        Project project=projectService.updateProject(id,updateProjectRequestDto);
        return new ResponseEntity<>( projectMapper.toDto(project),HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects()
    {
        List<Project> projects=projectService.getAllProjects();
        List<ProjectDto> projectDtos=projects.stream().map(projectMapper::toDto).toList();
        return new ResponseEntity<>(projectDtos,HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Map<String,String>> deleteProject(@PathVariable UUID projectId)
    {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok(Map.of("message", "Project deleted successfully"));

    }


}
