package com.manager.attendenceleavemanagementsystem.dto;

public class LoginResponse {
    private String token;
    private Long employeeId;
    private String role;
    private String empName;

    public LoginResponse(Long employeeId, String role, String token, String empName) {
        this.employeeId = employeeId;
        this.empName = empName;
        this.role = role;
        this.token = token;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getToken() {
        return token;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
