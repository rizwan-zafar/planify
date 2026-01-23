package com.planify.domain.service.impl;

import com.planify.repository.TaskRepository;
import com.planify.domain.CreateTaskRequest;
import com.planify.domain.UpdateTaskRequest;
import com.planify.domain.entity.Task;
import com.planify.domain.entity.TaskStatus;
import com.planify.domain.exception.TaskNotFoundException;
import com.planify.domain.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

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

    @Override
    public Task updateTask(UUID id, UpdateTaskRequest request) {

        Task task= taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setPriority(request.priority());
        task.setStatus(request.status());
        return taskRepository.save(task);
    }

    @Override
    public void deteTask(UUID id) {
         taskRepository.deleteById(id);
    }
}
