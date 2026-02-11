package com.planify.domain.controller;

import com.planify.domain.dto.AdminDto;
import com.planify.domain.dto.CreateAdminRequestDto;
import com.planify.domain.dto.ManagerDto;
import com.planify.domain.entity.Admin;
import com.planify.domain.mapper.AdminMapper;
import com.planify.domain.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminMapper adminMapper;


    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@Valid @RequestBody CreateAdminRequestDto requestDto)
    {
        Admin admin=adminService.createAdmin(requestDto);
        return new ResponseEntity<>(adminMapper.toDto(admin), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdminDto>> listAdmin()
    {
        List<Admin> adminList=adminService.listAdmin();
        List<AdminDto> adminDtoList = adminList.stream().map(x->adminMapper.toDto(x)).toList();
        return new ResponseEntity<>(adminDtoList,HttpStatus.OK);
    }
}
