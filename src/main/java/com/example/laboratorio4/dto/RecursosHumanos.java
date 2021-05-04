package com.example.laboratorio4.dto;

import java.time.LocalDate;

public interface RecursosHumanos {
    String getFirst_name();
    String getLast_name();
    String getJob_title();
    LocalDate getStart_date();
    LocalDate getEnd_date();
    int getAnios();
}
