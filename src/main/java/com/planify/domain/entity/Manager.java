package com.planify.domain.entity;

import com.planify.domain.enums.ManagerGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="name", nullable = false)
    private String name;

    @Column (name="email", nullable = false,updatable = false)
    private String email;

    @Column(name="department")
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private ManagerGender gender;

    @Column(name="updated")
    private Instant updated;

    @Column(name="created")
    private Instant created;


    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    private List<Project> projects;

}

// request
// name
// email
// department
// gender

//Respone
// id
// name
// email
// department
// gender

