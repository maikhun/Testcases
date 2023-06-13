package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "project_set")
@Data
public class SetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "setName")
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private ProjectEntity project;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "set")
    private List<CaseEntity> cases = new LinkedList<>();

    public int getSize() {
        int size = cases.size();
        if (size == 0) {
            return 0;
        }
        return size;
    }
}
