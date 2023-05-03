package com.example.testproj.models;

import jdk.jfr.Name;

import javax.persistence.*;
import java.util.Date;
import java.util.Queue;
import java.util.Set;

@Entity
@Table(name = "case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name")
    private String name;

    @Column(name = "deadline")
    private Date date;

    @OneToMany
    private Set<Step> steps;

    public Case() {
    }
    public Case(String name, Date date) {
        this.name = name;
        this.date = date;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Date getDate() {
        return date;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String phone) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "id: " + id + "\nИмя тест-кейса: " +
                name + "\nДата: " + date;
    }

}
