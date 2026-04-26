package com.manager.attendenceleavemanagementsystem.service;

import com.manager.attendenceleavemanagementsystem.dto.LoginResponse;

public interface AuthenticationService {
    public LoginResponse employeeLoginRequeest(String email, String password);
}
