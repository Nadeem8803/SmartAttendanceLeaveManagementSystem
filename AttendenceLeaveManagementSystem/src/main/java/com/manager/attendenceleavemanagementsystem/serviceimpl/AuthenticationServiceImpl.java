package com.manager.attendenceleavemanagementsystem.serviceimpl;

import com.manager.attendenceleavemanagementsystem.dto.LoginResponse;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.repository.EmployeeRepository;
import com.manager.attendenceleavemanagementsystem.service.AuthenticationService;
import com.manager.attendenceleavemanagementsystem.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthenticationServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public LoginResponse employeeLoginRequeest(String email, String password) {

        Employee employee = employeeRepository.findByEmpEmail(email).orElseThrow(() -> new RuntimeException("user not found"));


        if(! passwordEncoder.matches(password,employee.getEmpPassword())){
            throw new RuntimeException("wrong password");
        }

        String token = jwtUtil.generateToken(
                employee.getEmpEmail(),
                employee.getEmpAuthenticationRole()
        );

        return new LoginResponse(token,employee.getEmpId(),employee.getEmpAuthenticationRole());
    }
}
