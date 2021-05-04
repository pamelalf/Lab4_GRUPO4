package com.example.laboratorio4.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ListaEmpleadosxSueldo {
    int getEmployee_id();
    String getFirst_name();
    String getLast_name();
    LocalDate getStart_date();
    LocalDate getEnd_date();
    String getJob_title();
    BigDecimal getSalary();
}
