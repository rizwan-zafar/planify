package com.planify.domain.service;

import com.planify.domain.dto.AdminDto;
import com.planify.domain.dto.CreateAdminRequestDto;

public interface AdminService {
    AdminDto createAdmin(CreateAdminRequestDto request);
}
