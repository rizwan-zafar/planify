package com.planify.domain.service;

import com.planify.domain.CreateTaskRequest;
import com.planify.domain.UpdateTaskRequest;
import com.planify.domain.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
    List<Task> listTasks();
    Task updateTask(UUID id, UpdateTaskRequest request);
    void deteTask(UUID id);
}
