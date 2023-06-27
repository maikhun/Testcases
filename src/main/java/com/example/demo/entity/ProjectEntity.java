package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Проект
 * */
@Entity
@Table(name = "project")
@Data
public class ProjectEntity {
    // Идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название
    @Column(name = "projectName")
    private String name;

    // Описание
    @Column(name = "projectDescription")
    private String description;

    // Наборы, которые принадлежат проекту
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<SetEntity> sets = new LinkedList<>();

    // Компания, которой принадлежит проект
    @ManyToOne
    private CompanyEntity company;

}
