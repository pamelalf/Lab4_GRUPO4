package com.example.laboratorio4.entity;

import com.sun.istack.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @Column(name="first_name")
    @NotBlank(message = "no puede estar vacío")
    private String firstName;
    @Column(name="last_name")
    @NotBlank(message = "no puede estar vacío")
    private String lastName;

    @NotBlank(message = "no puede estar vacío")
    @Email(message="Debe tener el formato nombre@correo.com")
    private String email;

    @NotBlank(message = "No debe ser vacío o blanco")
    @Size(min = 8, message = "Ingrese como mínimo 8 caracteres")
    private String password;

    @NotNull(message = "no puede estar vacío")
    @Min(value = 0, message = "Tiene que ser mayor a 0")
    @Digits(message = "Debe ser un número", integer = 8, fraction = 2)
    private BigDecimal salary;

    @Column(name="hire_date")
    private Date hiredate;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employees manager;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotNull(message = "no puede estar vacío")
    private Departments department;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @NotNull(message = "no puede estar vacío")
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

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Employees getManager() {
        return manager;
    }

    public void setManager(Employees manager) {
        this.manager = manager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //COMPLETAR relacion con otras tablas
}
