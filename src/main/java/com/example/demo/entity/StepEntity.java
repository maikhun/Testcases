package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class StepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "factRes")
    private String factRes;

    @Column(name = "waitRes")
    private String waitRes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "caseId")
    private CaseEntity caseId;
}
