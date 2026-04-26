package com.manager.attendenceleavemanagementsystem.controller;

import com.manager.attendenceleavemanagementsystem.dto.LoginRequest;
import com.manager.attendenceleavemanagementsystem.dto.LoginResponse;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> employeeLoginRequeest(@RequestBody LoginRequest loginRequest){

        LoginResponse loginResponse = authenticationService.employeeLoginRequeest(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(loginResponse);
    }
}
