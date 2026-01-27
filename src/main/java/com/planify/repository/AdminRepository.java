package com.planify.repository;

import com.planify.domain.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<UUID, Admin> {
}
