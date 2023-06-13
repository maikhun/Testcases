package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "project")
@Data
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "projectName")
    private String name;

    @Column(name = "projectDescription")
    private String description;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<SetEntity> sets = new LinkedList<>();

    @ManyToOne
    private CompanyEntity company;

}
