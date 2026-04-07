package com.manager.attendenceleavemanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AttendenceResponseDTO {

    private Long attendenceId;
    private LocalDate attendenceDate;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String attendenceStatus;

}
