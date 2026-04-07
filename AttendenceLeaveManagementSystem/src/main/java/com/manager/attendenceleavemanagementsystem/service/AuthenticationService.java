package com.manager.attendenceleavemanagementsystem.service;

import com.manager.attendenceleavemanagementsystem.entity.Employee;

public interface AuthenticationService {
    public String employeeLoginRequeest(String email,String password);
}
