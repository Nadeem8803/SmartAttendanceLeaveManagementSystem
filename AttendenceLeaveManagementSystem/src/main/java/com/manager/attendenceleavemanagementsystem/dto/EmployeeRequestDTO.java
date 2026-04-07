package com.manager.attendenceleavemanagementsystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

public class EmployeeRequestDTO {
    private String empName;
    private String empEmail;
    private String empRole;
    private String empDepartment;
    private String empAuthenticationRole;
    private String empPassword;
}
