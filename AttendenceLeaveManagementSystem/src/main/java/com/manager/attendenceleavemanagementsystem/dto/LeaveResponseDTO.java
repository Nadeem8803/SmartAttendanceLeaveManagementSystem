package com.manager.attendenceleavemanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveResponseDTO {
    private Long leaveId;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveReason;
    private LocalDate appliedDate;
    private String leaveStatus;
    private int totalDays;
}
