package com.planify.domain.controller;

import com.planify.domain.dto.CreateManagerRequestDto;
import com.planify.domain.dto.ManagerDto;
import com.planify.domain.dto.UpdateManagerRequestDto;
import com.planify.domain.entity.Manager;
import com.planify.domain.mapper.ManagerMapper;
import com.planify.domain.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerMapper managerMapper;


    @PostMapping
    public ResponseEntity<ManagerDto> createManager(CreateManagerRequestDto requestDto)
    {
        Manager manager=managerService.createManager(requestDto);
        return new ResponseEntity<>(managerMapper.toDto(manager), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ManagerDto> getAllManagers()
    {
       List<Manager> managers= managerService.getAllManager();
       List<ManagerDto> managerDtos=managers.stream().map(x->managerMapper.toDto(x)).toList();
       return managerDtos;
    }

    @PutMapping
    public ResponseEntity<ManagerDto> updateManger(UUID id, UpdateManagerRequestDto request){
         Manager manager=managerService.updateManager(request, id);
         return new ResponseEntity<>(managerMapper.toDto(manager),HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Map<String,String>> deleteManger(UUID id)
    {
        managerService.deleteManager(id);
        return ResponseEntity.ok(Map.of("message","Manager deleted successfully"));

    }
}
