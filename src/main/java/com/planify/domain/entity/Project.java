package com.planify.domain.entity;

import com.planify.domain.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id", updatable = false,nullable = false)
    private UUID id;

    @Column(name="taskId",updatable = true,nullable = true)
    private UUID task;

    @Column(name="name",updatable = true,nullable = false)
    private String name;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="startDate",nullable = false)
    private LocalDate startDate;

    @Column(name ="endDate",nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private ProjectStatus status;

    @Column(name="created",nullable = false)
    Instant created;

    @Column(name = "updated",nullable = false)
    Instant updated;

    @ManyToOne
    @JoinColumn(name="manager_id",nullable = true)
    private Manager manager;

}