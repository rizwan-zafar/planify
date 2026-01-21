package com.planify.domain.mapper;

import com.planify.domain.CreateTaskRequest;
import com.planify.domain.dto.CreateTaskRequestDto;
import com.planify.domain.dto.TaskDto;
import com.planify.domain.entity.Task;

public interface TaskMapper {
    CreateTaskRequest fromDto(CreateTaskRequestDto dto);
    TaskDto toDto(Task task);
}
