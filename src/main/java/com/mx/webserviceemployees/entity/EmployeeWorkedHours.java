package com.mx.webserviceemployees.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "EMPLOYEE_WORKED_HOURS")
public class EmployeeWorkedHours {
    @Id
    @Column(name = "ID", columnDefinition = "NUMBER")
    private int id;

    @Column(name = "WORKED_HOURS", columnDefinition = "NUMBER")
    private int workedHours;

    @Column(name = "WORKED_DATE", columnDefinition = "DATE")
    private String workedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_ID")
    Employee employee;

    public EmployeeWorkedHours() {
    }

    public EmployeeWorkedHours(int id, int workedHours, String workedDate, Employee employee) {
        this.id = id;
        this.workedHours = workedHours;
        this.workedDate = workedDate;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public String getWorkedDate() {
        return workedDate;
    }

    public void setWorkedDate(String workedDate) {
        this.workedDate = workedDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeeWorkedHours{" +
                "id=" + id +
                ", workedHours=" + workedHours +
                ", workedDate='" + workedDate + '\'' +
                ", employee=" + employee +
                '}';
    }
}
