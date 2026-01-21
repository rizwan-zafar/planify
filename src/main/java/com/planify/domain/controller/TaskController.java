package com.planify.domain.controller;

import com.planify.domain.CreateTaskRequest;
import com.planify.domain.dto.CreateTaskRequestDto;
import com.planify.domain.dto.TaskDto;
import com.planify.domain.entity.Task;
import com.planify.domain.mapper.TaskMapper;
import com.planify.domain.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    private TaskService taskService;
    private TaskMapper taskMapper;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskRequestDto createTaskRequestDto)
    {
        Task task=taskService.createTask(taskMapper.fromDto(createTaskRequestDto));
        return new ResponseEntity<>(taskMapper.toDto(task), HttpStatus.CREATED);
    }

}
