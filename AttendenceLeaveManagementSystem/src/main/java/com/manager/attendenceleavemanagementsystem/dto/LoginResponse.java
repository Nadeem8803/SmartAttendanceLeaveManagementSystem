package com.manager.attendenceleavemanagementsystem.dto;

public class LoginResponse {
    private String token;
    private Long employeeId;
    private String role;

    public LoginResponse(  String token, Long employeeId, String role) {
        this.employeeId = employeeId;
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


}
