package com.mx.webserviceemployees.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEES")

public class Employee {
    @Id
    @Column(name = "ID", columnDefinition = "NUMBER")
    private int id;

    @Column(name = "NAME",columnDefinition = "NVARCHAR2(255)")
    private String name;

    @Column(name = "LAST_NAME",columnDefinition = "NVARCHAR2(255)")
    private String lastName;

    @Column(name = "BIRTHDATE",columnDefinition = "DATE")
    private String birthdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GENDER_ID")
    Gender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOB_ID")
    Job job;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    List<EmployeeWorkedHours> employeeWorkedHoursList = new ArrayList<>();

    public Employee() {
    }

    public Employee(int id, String name, String lastName, String birthdate, Gender gender, Job job) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.job = job;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", gender=" + gender +
                ", job=" + job +
                ", employeeWorkedHoursList=" + employeeWorkedHoursList +
                '}';
    }
}
