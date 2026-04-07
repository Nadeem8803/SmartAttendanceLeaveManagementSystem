package com.manager.attendenceleavemanagementsystem.serviceimpl;

import com.manager.attendenceleavemanagementsystem.config.PasswordConfig;
import com.manager.attendenceleavemanagementsystem.dto.EmployeeRequestDTO;
import com.manager.attendenceleavemanagementsystem.dto.EmployeeResponseDTO;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.repository.EmployeeRepository;
import com.manager.attendenceleavemanagementsystem.service.EmployeeService;
import com.manager.attendenceleavemanagementsystem.util.AuthrizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthrizationUtil authrizationUtil;
    private final PasswordEncoder passwordEncoder;

    EmployeeServiceImpl(EmployeeRepository employeeRepository, AuthrizationUtil authrizationUtil, PasswordConfig passwordConfig, PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.authrizationUtil = authrizationUtil;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public Employee createEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO, Employee loggedInEmployee) {
        authrizationUtil.requireAdmin(loggedInEmployee);
        Employee employee = new Employee();

        employee.setEmpName(employeeRequestDTO.getEmpName());
        employee.setEmpEmail(employeeRequestDTO.getEmpEmail());
        employee.setEmpDepartment(employeeRequestDTO.getEmpDepartment());
        employee.setEmpRole(employeeRequestDTO.getEmpRole());
        employee.setEmpAuthenticationRole(employeeRequestDTO.getEmpAuthenticationRole());
        employee.setEmpPassword(passwordEncoder.encode(employeeRequestDTO.getEmpPassword()));

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponse(savedEmployee);
    }

    private EmployeeResponseDTO mapToResponse(Employee employee){

        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();

        employeeResponseDTO.setEmpId(employee.getEmpId());
        employeeResponseDTO.setEmpCode(employee.getEmpCode());
        employeeResponseDTO.setEmpName(employee.getEmpName());
        employeeResponseDTO.setEmpEmail(employee.getEmpEmail());
        employeeResponseDTO.setEmpDepartment(employee.getEmpDepartment());
        employeeResponseDTO.setEmpRole(employee.getEmpRole());
        employeeResponseDTO.setEmpJoiningDate(employee.getEmpJoiningDate());
        employeeResponseDTO.setActive(employee.getActive());

        return employeeResponseDTO;

    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("employee not found with is :" + employeeId));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void updateEmployeeStatus(Long employeeId, Boolean active, Long loggedInEmployeeId) {

        Employee loggedInEmployee = employeeRepository
                .findById(loggedInEmployeeId)
                .orElseThrow(()-> new RuntimeException("employee not found with id : " + loggedInEmployeeId));

        authrizationUtil.requireAdmin(loggedInEmployee);

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("employee not found with is :" + employeeId));
        employee.setActive(active);
        employeeRepository.save(employee);
        System.out.println("scucess");
    }


    @Override
    public List<Employee> getEmployeeAtciveTrue() {
        return employeeRepository.findByActiveTrue();
    }

    @Override
    public List<Employee> getEmployeeActiveFalse() {
        return employeeRepository.findByActiveFalse();
    }


}
