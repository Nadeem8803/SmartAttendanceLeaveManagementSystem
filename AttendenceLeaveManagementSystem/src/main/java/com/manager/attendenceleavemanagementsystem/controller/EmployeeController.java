package com.manager.attendenceleavemanagementsystem.controller;

import com.manager.attendenceleavemanagementsystem.dto.EmployeeRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.EmployeeResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173",
allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS

        }
)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

//    @PostMapping
//    public Employee createEmployee(@RequestBody Employee employee){
//        return employeeService.createEmployee(employee);
//    }
    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO, @RequestParam Long loggedInEmployeeId){

        Employee loggedInEmployee = employeeService.getEmployeeById(loggedInEmployeeId);
        return employeeService.createEmployee(employeeRequestDTO,loggedInEmployee);
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/status/activeTrue")
    public List<Employee> getEmployeeActiveTrue(){
        return employeeService.getEmployeeAtciveTrue();
    }

    @GetMapping("/status/activeFalse")
    public List<Employee> getEmployeeActiveFalse(){
        return employeeService.getEmployeeActiveFalse();
    }


    @PutMapping("/active/{employeeId}")
    public void updateEmployeeStatus(@PathVariable Long employeeId,@RequestBody boolean active,@RequestParam Long loggedInEmployeeId){
        employeeService.updateEmployeeStatus(employeeId,active,loggedInEmployeeId);
    }


}
