package com.planify.domain.entity;

import com.planify.domain.enums.AdminRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="name",)
    private String name;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private AdminRole role;

    @Column(name="created")
    private Instant created;

    @Column(name="updated")
    private Instant updated;

}
