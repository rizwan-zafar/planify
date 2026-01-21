package com.planify.domain.service;

import com.planify.domain.CreateTaskRequest;
import com.planify.domain.entity.Task;

import java.util.List;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
    List<Task> listTasks();
}
