package com.manager.attendenceleavemanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDTO {
    private Long employeeId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveReason;
}
