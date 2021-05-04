package com.example.laboratorio4.dto;

import java.math.BigDecimal;

public interface EmployeeInfo {
    int getEmployee_id();
    String getFirst_name();
    String getLast_name();
    String getJob_title();
    BigDecimal getSalary();
}
