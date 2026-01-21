package com.planify.domain.service.impl;

import com.planify.TaskRepository;
import com.planify.domain.CreateTaskRequest;
import com.planify.domain.entity.Task;
import com.planify.domain.entity.TaskStatus;
import com.planify.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(CreateTaskRequest request) {
        Instant now=Instant.now();
        Task task= new Task(null, request.title(), request.description(),request.dueDate(), TaskStatus.OPEN,request.priority(),now,now);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC,"created"));
    }
}
