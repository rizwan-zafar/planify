package com.planify.domain.service;

import com.planify.domain.dto.AdminDto;
import com.planify.domain.dto.CreateAdminRequestDto;
import com.planify.domain.entity.Admin;

public interface AdminService {
    Admin createAdmin(CreateAdminRequestDto request);
}
