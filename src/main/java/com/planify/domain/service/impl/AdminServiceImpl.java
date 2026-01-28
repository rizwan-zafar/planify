package com.planify.domain.service.impl;

import com.planify.domain.dto.AdminDto;
import com.planify.domain.dto.CreateAdminRequestDto;
import com.planify.domain.entity.Admin;
import com.planify.domain.exception.AdminPasswordNotMatchException;
import com.planify.domain.mapper.AdminMapper;
import com.planify.domain.service.AdminService;
import com.planify.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin createAdmin(CreateAdminRequestDto request) {

        Instant now=Instant.now();

        if (!request.password().equals(request.confirm_password())) {
            throw new AdminPasswordNotMatchException("Password and Confirm Password do not match");
        }
        Admin admin=new Admin(null, request.name(), request.email(), request.password(), request.role(), now,now);
        return adminRepository.save(admin);
    }

}
