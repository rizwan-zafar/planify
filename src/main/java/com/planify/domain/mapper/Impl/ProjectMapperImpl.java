package com.planify.domain.mapper.Impl;

import com.planify.domain.dto.ProjectDto;
import com.planify.domain.entity.Project;
import com.planify.domain.exception.TaskNotFoundException;
import com.planify.domain.mapper.ManagerMapper;
import com.planify.domain.mapper.ProjectMapper;
import com.planify.domain.mapper.TaskMapper;
import com.planify.repository.ManagerRepository;
import com.planify.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public ProjectDto toDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getTask().stream().map(x -> taskMapper.toDto(taskRepository.findById(x)
                                        .orElseThrow(() -> new TaskNotFoundException(x)))).toList(),
                project.getStartDate(),
                project.getEndDate(),
                project.getStatus()
        );
    }
}
