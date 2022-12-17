package com.mx.webserviceemployees.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="GENDERS")
public class Gender {

    @Id
    @Column(name = "ID", columnDefinition = "NUMBER")
    private int id;

    @Column(name = "NAME", columnDefinition = "NVARCHAR2(255)")
    private String name;

    @OneToMany(mappedBy = "gender", cascade = CascadeType.ALL)
    List<Employee> employeesList = new ArrayList<>();

    public Gender() {
    }

    public Gender(int id, String name) {
        this.id = id;
        this.name = name;
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


    @Override
    public String toString() {
        return "Gender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeesList=" + employeesList +
                '}';
    }
}
