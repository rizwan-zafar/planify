package com.planify.domain.service;

import com.planify.domain.dto.CreateManagerRequestDto;
import com.planify.domain.dto.UpdateManagerRequestDto;
import com.planify.domain.entity.Manager;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    Manager createManager(CreateManagerRequestDto request);
    List<Manager> getAllManager();
    Manager updateManager(UpdateManagerRequestDto request, UUID id);
    void  deleteManager(UUID id);
}
