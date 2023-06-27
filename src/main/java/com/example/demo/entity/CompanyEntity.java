package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
/**
 * Компания
 * */
@Entity
@Table(name = "company")
@Data
public class CompanyEntity {
    // Идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    // Наименование
    @Column(name = "name")
    private String name;

    // Проекты, которые принадлежат компании
    @OneToMany(targetEntity = ProjectEntity.class, fetch = FetchType.EAGER)
    private List<ProjectEntity> projects;
}
