package com.manager.attendenceleavemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long attendenceId;

    @Column(name = "date", nullable = false)
    private LocalDate attendenceDate;

    @Column(name = "check_in")
    private LocalTime checkInTime;

    @Column(name = "check_out", nullable = true)
    private LocalTime checkOutTime;

    @Column(name = "status", nullable = false)
    private String attendenceStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @PrePersist
    public void prePersist(){
        this.checkInTime = LocalTime.now();
        this.attendenceDate = LocalDate.now();
    }
}
