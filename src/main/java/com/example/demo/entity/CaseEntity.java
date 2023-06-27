package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Тест-кейс
 * */
@Entity
@Table(name = "testCase")
@Data
public class CaseEntity {
    // Идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Название
    @Column(name = "caseName")
    private String name;

    // Приоритет
    @Column(name = "priority")
    private String priority;

    // Серьезность
    @Column(name = "seriousness")
    private String seriousness;

    // Статус
    @Column(name = "status")
    private String status;

    // Набор, к которому привязан тест-кейс
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private SetEntity set;

    // Шаги, находящиеся в тест-кейсе
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "caseId")
    private List<StepEntity> steps = new ArrayList<>();
}
