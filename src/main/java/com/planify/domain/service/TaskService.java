package com.planify.domain.service;

import com.planify.domain.CreateTaskRequest;
import com.planify.domain.entity.Task;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
}
