package com.mx.webserviceemployees.entity;


import jakarta.persistence.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JOBS")
public class Job {

    @Id
    @Column(name = "ID", columnDefinition = "NUMBER")
    private int id;

    @Column(name = "NAME", columnDefinition = "NVARCHAR2(255)")
    private String name;


    @Column(name = "SALARY",columnDefinition = "NUMBER")
    private double salary;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    List<Employee> employeesList = new ArrayList<>();

    public Job() {
    }

    public Job(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", employeesList=" + employeesList +
                '}';
    }
}
