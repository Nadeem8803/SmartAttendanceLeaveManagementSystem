package com.manager.attendenceleavemanagementsystem.controller;

import com.manager.attendenceleavemanagementsystem.dto.LoginRequest;
import com.manager.attendenceleavemanagementsystem.entity.Employee;
import com.manager.attendenceleavemanagementsystem.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> employeeLoginRequeest(@RequestBody LoginRequest loginRequest){

        String token = authenticationService.employeeLoginRequeest(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        return ResponseEntity.ok(token);
    }
}
