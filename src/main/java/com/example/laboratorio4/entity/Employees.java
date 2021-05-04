package com.example.laboratorio4.entity;

import com.sun.istack.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private float salary;
    private Date hiredate;
    @Column(nullable = true)
    private Integer managerId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments departament;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Departments getDepartament() {
        return departament;
    }

    public void setDepartament(Departments departament) {
        this.departament = departament;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //COMPLETAR relacion con otras tablas
}
