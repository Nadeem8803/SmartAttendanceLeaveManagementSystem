package com.manager.attendenceleavemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long empId;

    @Column(nullable = false, unique = true, name = "code")
    private String empCode;

    @Column(name = "name", nullable = false)
    private String empName;

    @Column(name = "email", nullable = false, unique = true)
    private String empEmail;

    @Column(name = "role", nullable = false)
    private String empRole;

    @Column(name = "department")
    private String empDepartment;

    @Column(name = "joining")
    private LocalDate empJoiningDate;

    @Column(name = "active",nullable = false)
    private Boolean active = true;

    @Column(name = "auth_role", nullable = false)
    private String empAuthenticationRole;//ADMIN,EMPLOYEE

    @Column(name = "password", nullable = false)
    private String empPassword;

    @PrePersist
    public void prePersistForActive(){
        if(active == null){
            active = true;
        }

        this.empJoiningDate = LocalDate.now();
        this.empCode = "EMP" + System.currentTimeMillis();

    }

}
