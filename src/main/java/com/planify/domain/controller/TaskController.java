package com.planify.domain.controller;

import com.planify.domain.CreateTaskRequest;
import com.planify.domain.UpdateTaskRequest;
import com.planify.domain.dto.CreateTaskRequestDto;
import com.planify.domain.dto.TaskDto;
import com.planify.domain.dto.UpdateTaskRequestDto;
import com.planify.domain.entity.Task;
import com.planify.domain.mapper.TaskMapper;
import com.planify.domain.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskMapper taskMapper;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskRequestDto createTaskRequestDto)
    {
        CreateTaskRequest createTaskRequest=taskMapper.fromDto(createTaskRequestDto);
        Task task=taskService.createTask(createTaskRequest);


        return new ResponseEntity<>(taskMapper.toDto(task), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks()
    {
        List<Task> tasks=taskService.listTasks();
        List<TaskDto> taskDtoList=tasks.stream().map(taskMapper::toDto).toList();
        return new ResponseEntity<>(taskDtoList, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @Valid @RequestBody UpdateTaskRequestDto updateTaskRequestDto)
    {
        UpdateTaskRequest updateTaskRequest=taskMapper.fromDto(updateTaskRequestDto);
        Task task=taskService.updateTask(taskId,updateTaskRequest);
        TaskDto taskDto=taskMapper.toDto(task);
        return  ResponseEntity.ok(taskDto);

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable UUID taskId)
    {

        taskService.deteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
