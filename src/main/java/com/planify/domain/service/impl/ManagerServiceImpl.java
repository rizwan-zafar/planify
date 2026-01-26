package com.planify.domain.service.impl;

import com.planify.domain.dto.CreateManagerRequestDto;
import com.planify.domain.dto.ManagerDto;
import com.planify.domain.dto.UpdateManagerRequestDto;
import com.planify.domain.entity.Manager;
import com.planify.domain.entity.Project;
import com.planify.domain.exception.ManagerNotFoundException;
import com.planify.domain.exception.TaskNotFoundException;
import com.planify.domain.service.ManagerService;
import com.planify.repository.ManagerRepository;
import com.planify.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Manager createManager(CreateManagerRequestDto request) {
        System.out.println("Created Server" + request);
        Instant now=Instant.now();
        Manager manager=new Manager(null,request.name(),request.email(),
                request.department(), request.gender(),now,now,new ArrayList<>());
        List<Project> projects = projectRepository.findAllById(request.projectIds());
        for (Project project : projects) {
            project.setManager(manager);
        }
       return managerRepository.save(manager);
    }

    @Override
    public List<Manager> getAllManager() {
        return managerRepository.findAll();
    }

    @Override
    public Manager updateManager(UpdateManagerRequestDto request, UUID id) {
       Manager manager=managerRepository.findById(id).orElseThrow(()-> new ManagerNotFoundException(id));
       manager.setName(request.name());
       manager.setDepartment(request.department());
       manager.setEmail(request.email());
       manager.setGender(manager.getGender());
       return managerRepository.save(manager);
    }

    @Override
    public void deleteManager(UUID id) {
        if(managerRepository.existsById(id))
        {
            managerRepository.deleteById(id);
        }
    }
}
