package com.manager.attendenceleavemanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data

public class EmployeeResponseDTO {
    private Long empId;
    private String empCode;
    private String empName;
    private String empEmail;
    private String empRole;
    private String empDepartment;
    private LocalDate empJoiningDate;
    private Boolean active;
}
