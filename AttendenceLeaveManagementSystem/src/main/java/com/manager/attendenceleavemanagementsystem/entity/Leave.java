package com.manager.attendenceleavemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "leaves")//beacuse leaves are sql keyword
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long leaveId;

    @Column(name = "leave_type", nullable = false)
    private String leaveType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "total_days", nullable = false)
    private int totalDays;

    @Column(name = "reason", nullable = false)
    private String leaveReason;

    @Column(name = "applied_date", nullable = false)
    private LocalDate appliedDate;

    @Column(name = "status", nullable = false)
    private String leaveStatus; // PENDING,APPROVED,REJECTED


    @PrePersist
    public void prePersist(){
        this.appliedDate = LocalDate.now();
        this.leaveStatus = "PENDING";
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}
