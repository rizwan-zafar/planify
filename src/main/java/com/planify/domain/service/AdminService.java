package com.planify.domain.service;

import com.planify.domain.dto.CreateAdminRequestDto;
import com.planify.domain.entity.Admin;

import java.util.List;

public interface AdminService {
    Admin createAdmin(CreateAdminRequestDto request);
    List<Admin> listAdmin();

}
