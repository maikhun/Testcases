package com.example.testproj.models;

import jakarta.persistence.*;

@Entity
@Table(name = "step")
public class Step {
    @Id
    @GeneratedValue()
    private Long id;

    @Column
    private String description;

    protected Step() {
    }

    public Step(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
