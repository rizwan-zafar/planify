package com.planify.domain.entity;
import com.planify.domain.enums.TaskPriority;
import com.planify.domain.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id", updatable = false,nullable = false)
    private UUID id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description", length = 1000)
    private String description;

    @Column(name="due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name="priority",nullable = false)
    private TaskPriority priority;

    @Column(name="created", updatable = false,nullable = false)
    private Instant created;

    @Column(name="updated",nullable = false)
    private Instant updated;

}
