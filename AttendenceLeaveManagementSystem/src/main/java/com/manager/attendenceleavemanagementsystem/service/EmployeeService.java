package com.manager.attendenceleavemanagementsystem.service;
import com.manager.attendenceleavemanagementsystem.dto.EmployeeRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.EmployeeResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Employee;

import java.util.List;


public interface EmployeeService {
//    public Employee createEmployee(Employee employee);
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO, Employee loggedInEmployee);
    public Employee getEmployeeById(Long employeeId);
    public List<Employee> getAllEmployees();
    public void updateEmployeeStatus(Long employeeId,Boolean active);
    public List<Employee> getEmployeeAtciveTrue();
    public List<Employee> getEmployeeActiveFalse();
}
