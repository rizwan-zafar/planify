package com.planify.domain.mapper.Impl;

import com.planify.domain.dto.AdminDto;
import com.planify.domain.entity.Admin;
import com.planify.domain.mapper.AdminMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminMapperImpl implements AdminMapper {
    @Override
    public AdminDto toDto(Admin admin) {
            return new AdminDto(
                    admin.getName(),
                    admin.getEmail(),
                    admin.getRole());

    }
}
